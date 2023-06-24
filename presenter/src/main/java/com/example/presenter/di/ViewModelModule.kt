package com.example.presenter.di

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import com.example.presenter.borrow.BorrowViewModelImpl
import com.example.presenter.convert.ConvertViewModelImpl
import com.example.presenter.currency.CurrencyViewModelImpl
import com.example.presenter.history.HistoryViewModelImpl
import com.example.presenter.home.HomeViewModelImpl
import com.example.presenter.income.InComeViewModelImpl
import com.example.presenter.lend.LendViewModelImpl
import com.example.presenter.outCome_currencies.OutComeCurrenciesViewMImpl
import com.example.presenter.outcome.OutComeViewModelImpl
import com.example.presenter.persons.PersonViewModelImpl
import com.example.presenter.signUp.SignUpViewModelImpl
import com.example.presenter.signin.SignInViewModelImpl
import com.example.presenter.wallets.WalletsViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
internal interface ViewModelModule {

    @[Binds IntoMap ScreenModelKey(SignInViewModelImpl::class)]
    fun signInViewModelImpl(impl: SignInViewModelImpl): ScreenModel

    @[Binds IntoMap ScreenModelKey(SignUpViewModelImpl::class)]
    fun signUpViewModelImpl(impl: SignUpViewModelImpl): ScreenModel

    @[Binds IntoMap ScreenModelKey(HomeViewModelImpl::class)]
    fun homeViewModelImpl(impl: HomeViewModelImpl): ScreenModel

    @[Binds IntoMap ScreenModelKey(CurrencyViewModelImpl::class)]
    fun currencyViewModelImpl(impl: CurrencyViewModelImpl): ScreenModel

    @[Binds IntoMap ScreenModelKey(WalletsViewModelImpl::class)]
    fun provideWalletsViewModel(impl: WalletsViewModelImpl): ScreenModel

    @[Binds IntoMap ScreenModelKey(InComeViewModelImpl::class)]
    fun provideInComeViewModel(impl: InComeViewModelImpl): ScreenModel

    @[Binds IntoMap ScreenModelKey(OutComeViewModelImpl::class)]
    fun outComeViewModelImpl(impl: OutComeViewModelImpl): ScreenModel

    @[Binds IntoMap ScreenModelKey(OutComeCurrenciesViewMImpl::class)]
    fun outComeCurrenciesViewMImpl(impl: OutComeCurrenciesViewMImpl): ScreenModel

    @[Binds IntoMap ScreenModelKey(PersonViewModelImpl::class)]
    fun personViewModelImpl(impl: PersonViewModelImpl): ScreenModel

    @[Binds IntoMap ScreenModelKey(BorrowViewModelImpl::class)]
    fun borrowViewModelImpl(impl: BorrowViewModelImpl): ScreenModel

    @[Binds IntoMap ScreenModelKey(LendViewModelImpl::class)]
    fun lendViewModelImpl(impl: LendViewModelImpl): ScreenModel

    @[Binds IntoMap ScreenModelKey(ConvertViewModelImpl::class)]
    fun convertViewModelImpl(impl: ConvertViewModelImpl): ScreenModel

    @[Binds IntoMap ScreenModelKey(HistoryViewModelImpl::class)]
    fun historyViewModelImpl(impl: HistoryViewModelImpl): ScreenModel
}