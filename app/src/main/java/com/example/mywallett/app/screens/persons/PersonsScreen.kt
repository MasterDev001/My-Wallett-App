package com.example.mywallett.app.screens.persons

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import com.example.a_common.data.PersonData
import com.example.mywallett.R
import com.example.mywallett.app.utils.DialogAlert
import com.example.mywallett.app.utils.DialogConfirm
import com.example.mywallett.app.utils.DialogPerson
import com.example.mywallett.app.utils.PersonItem
import com.example.mywallett.app.utils.PopUpToDialog
import com.example.mywallett.app.utils.horizontalPadding_16
import com.example.mywallett.ui.theme.ColorGreenButton
import com.example.presenter.persons.PersonContract
import com.example.presenter.persons.PersonViewModel
import kotlinx.coroutines.launch
import uz.gita.vogayerlib.hiltScreenModel
import java.util.UUID


@OptIn(ExperimentalFoundationApi::class)
class PersonsScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: PersonViewModel = hiltScreenModel()
        val uiState = viewModel.uiState.collectAsState()
        PersonsScreenContent(uiState, viewModel, viewModel::onEventDispatcher)
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    private fun PersonsScreenContent(
        uiState: State<PersonContract.UiState>,
        viewModel: PersonViewModel,
        onEvent: (PersonContract.Intent) -> Unit
    ) {
        var addPersonDialogState by remember { mutableStateOf(false) }
        val tabList = listOf(
            stringResource(R.string.hamma),
            stringResource(id = R.string.haqdorlar),
            stringResource(id = R.string.qarzdorlar)
        )
        val pagerState = rememberPagerState()
        val coroutine = rememberCoroutineScope()
        val persons by viewModel.persons.collectAsState(initial = emptyList())
        var popUpState by remember { mutableStateOf(false) }
        var deleteDialogState by remember { mutableStateOf(false) }
        var editDialogState by remember { mutableStateOf(false) }
        var errorDialogState by remember { mutableStateOf(false) }
        var currentPerson by remember { mutableStateOf(PersonData("")) }
        var offsetPopUp by remember { mutableStateOf(Offset(0f, 0f)) }
        val debtors by viewModel.getAllDebtors().collectAsState(initial = emptyList())
        val lenders by viewModel.getAllLenders().collectAsState(initial = emptyList())

        val debtorsList = persons.filter { person ->
            debtors.map { personCurrency ->
                personCurrency.personId
            }.contains(person.id)
        }
        val lenderList = persons.filter { person ->
            lenders.map { personCurrency ->
                personCurrency.personId
            }.contains(person.id)
        }

        if (addPersonDialogState) {
            DialogPerson(
                onDismissRequest = { addPersonDialogState = false },
                viewModel::isPersonExist,
                onAddClick = { name, phoneNumber, address ->
                    onEvent.invoke(
                        PersonContract.Intent.AddPerson(
                            PersonData(
                                UUID.randomUUID().toString(),
                                name,
                                address,
                                phoneNumber,
                                System.currentTimeMillis()
                            )
                        )
                    )
                })
        }

        if (popUpState) {
            PopUpToDialog(
                text = currentPerson.name,
                offset = offsetPopUp,
                onDismissRequest = { popUpState = false },
                onEditClick = { editDialogState = true },
                onDeleteClick = { deleteDialogState = true })
        }

        if (deleteDialogState) {
            popUpState = false
            DialogConfirm(
                text = currentPerson.name,
                message = currentPerson.name + stringResource(R.string.ni_o_chirishga_ishonchingiz_komilmi),
                onDismiss = { deleteDialogState = false },
                onConfirm = {
                    if (viewModel.isPersonCurrencyExist(currentPerson.id)) {
                        errorDialogState = true
                    } else {
                        onEvent.invoke(PersonContract.Intent.DeletePerson(currentPerson.id))
                    }
                })
        }

        if (editDialogState) {
            popUpState = false
            DialogPerson(
                onDismissRequest = { editDialogState = false },
                isPersonExist = viewModel::isPersonExist,
                namePlaceholder = currentPerson.name,
                phonePlaceholder = currentPerson.phoneNumber,
                addressPlaceholder = currentPerson.address,
                onAddClick = { name, phone, address ->
                    onEvent.invoke(
                        PersonContract.Intent.UpdatePerson(
                            currentPerson.copy(
                                name = name,
                                phoneNumber = phone,
                                address = address,
                                date = System.currentTimeMillis()
                            )
                        )
                    )
                }
            )
        }

        if (errorDialogState) {
            DialogAlert(
                text = stringResource(R.string.bu_shaxs_bilan_muomala_qilingan_o_chirish_mumkin_emas),
                onDismissRequest = {
                    errorDialogState = false
                })
        }
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.shaxslar)) },
                navigationIcon = {
                    IconButton(onClick = {
                        onEvent.invoke(PersonContract.Intent.OpenHome)
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                    }
                })

        }, floatingActionButton =
        {
            FloatingActionButton(onClick = {
                addPersonDialogState = true
            }, backgroundColor = ColorGreenButton) {
                Image(
                    painter = painterResource(id = R.drawable.add_white),
                    contentDescription = "Add"
                )
            }
        })
        {
            Column {
                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    backgroundColor = Color.White,
                    contentColor = Color.Gray,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            color = ColorGreenButton,
                            modifier = Modifier
                                .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                                .fillMaxWidth()
                        )
                    }
                ) {
                    tabList.forEachIndexed { index, title ->
                        Tab(
                            selected = pagerState.currentPage == index,
                            onClick = { coroutine.launch { pagerState.animateScrollToPage(index) } },
                            modifier = Modifier.padding(8.dp),
                            content = {
                                Text(
                                    text = title,
                                    modifier = Modifier
                                        .padding(8.dp),
                                    color = if (pagerState.currentPage == index) ColorGreenButton else Color.Gray
                                )

                            }
                        )
                    }
                }
                HorizontalPager(
                    pageCount = tabList.size,
                    state = pagerState,
                    modifier = Modifier
                        .background(Color.Gray)
                        .padding(horizontal = horizontalPadding_16)
                        .fillMaxSize(),
                ) {
                    var personsList by remember { mutableStateOf(listOf<PersonData>()) }
                    when (it) {
                        0 -> {
                            personsList = persons
                        }

                        1 -> {
                            personsList = lenderList
                        }

                        2 -> {
                            personsList = debtorsList
                        }
                    }
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(count = personsList.count(), itemContent = {
                            val person = personsList[it]
                            PersonItem(
                                person = person,
                                viewModel = viewModel,
                                offsetPopUp = offsetPopUp,
                                onItemClick = {
                                    onEvent.invoke(PersonContract.Intent.OpenPersonHistory(person))
                                },
                                onMenuMoreClicked = { offset, state ->
                                    offsetPopUp = offset
                                    popUpState = state
                                    currentPerson = person
                                }
                            )
                        })
                    }
                }
            }
        }
    }
}
