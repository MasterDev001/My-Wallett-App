package com.example.mywallett.di

import com.example.mywallett.app.screens.borrow.BorrowDirectionImpl
import com.example.mywallett.app.screens.convert.ConvertDirectionImpl
import com.example.mywallett.app.screens.currencies.CurrencyDirectionImpl
import com.example.mywallett.app.screens.history.HistoryDirectionImpl
import com.example.mywallett.app.screens.home.HomeDirectionImpl
import com.example.mywallett.app.screens.income.InComeDirectionImpl
import com.example.mywallett.app.screens.lend.LendDirectionImpl
import com.example.mywallett.app.screens.outcome.OutComeDirectionImpl
import com.example.mywallett.app.screens.outcome.outcome_currencies.OutComeCurrenciesDirecImpl
import com.example.mywallett.app.screens.persons.PersonDirectionImpl
import com.example.mywallett.app.screens.persons.personHistory.PersonHistoryDirectionImpl
import com.example.mywallett.app.screens.register.RegisterDirectionImpl
import com.example.mywallett.app.screens.share.ShareDirectionImpl
import com.example.mywallett.app.screens.signin.SignInDirectionImpl
import com.example.mywallett.app.screens.wallets.WalletsDirectionImpl
import com.example.mywallett.app.screens.wallets.walletHistory.WalletHistoryDirectionImpl
import com.example.presenter.borrow.BorrowDirection
import com.example.presenter.convert.ConvertDirection
import com.example.presenter.currency.CurrencyDirection
import com.example.presenter.history.HistoryDirection
import com.example.presenter.home.HomeDirection
import com.example.presenter.income.InComeDirection
import com.example.presenter.lend.LendDirection
import com.example.presenter.outCome_currencies.OutComeCurrenciesDirection
import com.example.presenter.outcome.OutComeDirection
import com.example.presenter.persons.PersonDirection
import com.example.presenter.persons.personHistory.PersonHistoryDirection
import com.example.presenter.share.ShareDirection
import com.example.presenter.signUp.SignUpDirection
import com.example.presenter.signin.SignInDirection
import com.example.presenter.wallets.WalletsDirection
import com.example.presenter.wallets.walletHistory.WalletHistoryDirection
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DirectionModule {

    @Binds
    fun signInDirection(impl: SignInDirectionImpl): SignInDirection

    @Binds
    fun shareDirection(impl: ShareDirectionImpl): ShareDirection

    @Binds
    fun signUpDirection(impl: RegisterDirectionImpl): SignUpDirection

    @Binds
    fun homeDirection(impl: HomeDirectionImpl): HomeDirection

    @Binds
    fun currencyDirection(impl: CurrencyDirectionImpl): CurrencyDirection

    @Binds
    fun provideWalletsDirection(impl: WalletsDirectionImpl): WalletsDirection

    @Binds
    fun provideInComeDirection(impl: InComeDirectionImpl): InComeDirection

    @Binds
    fun provideOutComeDirection(impl: OutComeDirectionImpl): OutComeDirection

    @Binds
    fun provideOutComeCurrenciesDirect(impl: OutComeCurrenciesDirecImpl): OutComeCurrenciesDirection

    @Binds
    fun providePersonDirection(impl: PersonDirectionImpl): PersonDirection

    @Binds
    fun provideBorrowDirection(impl: BorrowDirectionImpl): BorrowDirection

    @Binds
    fun provideLendDirection(impl: LendDirectionImpl): LendDirection

    @Binds
    fun provideConvertDirection(impl: ConvertDirectionImpl): ConvertDirection

    @Binds
    fun provideHistoryDirection(impl: HistoryDirectionImpl): HistoryDirection

    @Binds
    fun providePersonHistoryDirection(impl: PersonHistoryDirectionImpl): PersonHistoryDirection

    @Binds
    fun provideWalletHistoryDirection(impl: WalletHistoryDirectionImpl): WalletHistoryDirection

}