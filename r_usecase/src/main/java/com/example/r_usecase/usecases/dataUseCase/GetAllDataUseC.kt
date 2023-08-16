package com.example.r_usecase.usecases.dataUseCase

import android.content.Context
import android.util.Log
import com.example.a_common.BALANCE
import com.example.a_common.DEBTORS
import com.example.a_common.HISTORY
import com.example.a_common.LENDERS
import com.example.a_common.Type
import com.example.a_common.WALLETS
import com.example.a_common.getTypeEnum
import com.example.a_common.getTypeNumber
import com.example.a_common.getTypeText
import com.example.a_common.huminize
import com.example.a_common.huminizeForFile
import com.example.r_usecase.R
import com.example.r_usecase.usecases.currencyUseCase.CurrencyUseCase
import com.example.r_usecase.usecases.historyUseCase.HistoryUseCase
import com.example.r_usecase.usecases.personCurrencyUseCase.PersonCurrencyUseCase
import com.example.r_usecase.usecases.personUseCase.PersonsUseCase
import com.example.r_usecase.usecases.walletsUseCase.WalletsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllDataUseC @Inject constructor(
    private val personsUseCase: PersonsUseCase,
    private val walletsUseCase: WalletsUseCase,
    private val personCurrencyUseCase: PersonCurrencyUseCase,
    private val currencyUseCase: CurrencyUseCase,
    private val historyUseCase: HistoryUseCase
) {

    suspend operator fun invoke(context: Context): Flow<LinkedHashMap<String, LinkedHashMap<String, List<String>>>> =
        flow {
            val totalBalance = currencyUseCase.getTotalBalanceUseC.invoke()
            val currencyList = currencyUseCase.getAllCurrencies.invoke()
            val walletList = walletsUseCase.getAllWalletsUseC.invoke()
            val debtorsList = personCurrencyUseCase.getAllDebtors.invoke()
            val lendersList = personCurrencyUseCase.getAllLenders.invoke()
            val historyList = historyUseCase.getLimitedHistoryUseC.invoke(1000)

            val mainMap = LinkedHashMap<String, LinkedHashMap<String, List<String>>>()

            combine(
                currencyList,
                walletList,
                debtorsList,
                lendersList,
                historyList
            ) { currencies, wallets, debtors, lenders, histories ->
                val mapBalance = LinkedHashMap<String, List<String>>()
                val keyBalance =
                    context.getString(R.string.jami_balance) + totalBalance.huminize() + context.getString(
                        R.string.dollar
                    )
                val valueBalance = currencies.map { "${it.balance} ${it.name}" }
                mapBalance[keyBalance] = valueBalance


                val mapWallets = LinkedHashMap<String, List<String>>()
                wallets.onEach { w ->
                    val keyWallet = w.name
                    val valueWallet =
                        w.walletOwnerDataList.walletOwnerData.filter {
                            it.walletId == w.id
                        }.map {
                            "${it.currencyBalance} ${currencyUseCase.getCurrency.invoke(it.currencyId).name}"
                        }
                    mapWallets[keyWallet] = valueWallet
                }


                val mapDebtors = LinkedHashMap<String, List<String>>()
                debtors.onEach { d ->
                    val keyDebtors = personsUseCase.getPersonUseC.invoke(d.personId).name
                    val valueDebtors = mutableListOf<String>()
                    personCurrencyUseCase.getPersonCurriesByPersonIdUseC.invoke(d.personId)
                        .map {
                            it.map {
                                valueDebtors.add(
                                    "${it.currencyBalance} ${
                                        currencyUseCase.getCurrency.invoke(it.currencyId).name
                                    }"
                                )
                            }
                        }
                    mapDebtors[keyDebtors] = valueDebtors
                }


                val mapLenders = LinkedHashMap<String, List<String>>()
                lenders.onEach { l ->
                    val keyLenders = personsUseCase.getPersonUseC.invoke(l.personId).name
                    val valueLenders = mutableListOf<String>()
                    personCurrencyUseCase.getPersonCurriesByPersonIdUseC.invoke(l.personId)
                        .map {
                            it.map {
                                valueLenders.add(
                                    "${it.currencyBalance} ${
                                        currencyUseCase.getCurrency.invoke(it.currencyId).name
                                    }"
                                )
                            }
                        }
                    mapLenders[keyLenders] = valueLenders
                }


                val mapHistory = LinkedHashMap<String, List<String>>()
                histories.onEach { item ->

                    val list = ArrayList<String>()
                    var incr = ""
                    if (item.title == getTypeNumber(Type.INCOME) || item.title == getTypeNumber(Type.BORROW)) {
                        incr = "+"
                    } else if (item.title == getTypeNumber(Type.OUTCOME) || item.title == getTypeNumber(
                            Type.LEND
                        )
                    ) {
                        incr = "-"
                    }
                    val title = getTypeText(item.title)
                    val converted = "$incr${item.amount.huminize()} ${item.currency}"
                    list.add("$title: $converted")

                    if (!item.fromName.isNullOrEmpty()) {
                        val pocket =
                            if (item.isFromPocket) " " + context.getString(R.string.hamyon) else ""
                        val from = (item.fromName ?: "") + "${pocket}dan"
                        if (getTypeEnum(item.title) == Type.CONVERTATION) {
                            val textMinus = "-${item.moneyFrom?.huminize()} ${item.moneyNameFrom}"
                            list.add("$from $textMinus")
                        } else {
                            list.add(from)
                        }
                    }
                    if (!item.toName.isNullOrEmpty()) {
                        val pocket =
                            if (item.isToPocket) " " + context.getString(R.string.hamyon) else ""
                        val to = (item.toName ?: "") + "${pocket}ga"
                        if (getTypeEnum(item.title) == Type.CONVERTATION) {
                            val textPlus = "+${item.moneyTo?.huminize()} ${item.moneyNameTo}"
                            list.add("$to $textPlus")
                        } else {
                            list.add(to)
                        }
                    }

                    val kurs =
                        if (item.rateFrom > item.rateTo) {
                            "1 ${item.moneyNameTo} = ${(item.rateFrom / item.rateTo).huminize()} ${item.moneyNameFrom}"
                        } else if (item.rateFrom < item.rateTo) {
                            "1 ${item.moneyNameFrom} = ${(item.rateTo / item.rateFrom).huminize()} ${item.moneyNameTo}"
                        } else if (item.rateFrom != 1.0 && item.rateTo != 1.0) {
                            "1$ = ${item.rateTo.huminize()}"
                        } else ""
                    if (kurs.trim().isNotEmpty()) {
                        list.add("kurs: $kurs")
                    }
                    val oldBalance =
                        "${context.getString(R.string.bundan_oldingi_balans)} ${item.balance.huminize()} ${
                            context.getString(R.string.dollar)
                        }"
                    list.add(oldBalance)
                    var izoh = ""
                    item.comment?.let {
                        if (it.isNotEmpty()) {
                            izoh = "${context.getString(R.string.izoh)} $it"
                            list.add(izoh)
                        }
                    }
                    mapHistory[item.date.huminizeForFile()] = list
                }

                mainMap[BALANCE] = mapBalance
                mainMap[WALLETS] = mapWallets
                mainMap[DEBTORS] = mapDebtors
                mainMap[LENDERS] = mapLenders
                mainMap[HISTORY] = mapHistory
                Log.d("TAG12", "w: $mainMap")
                emit(mainMap)
            }.collect()
        }

}