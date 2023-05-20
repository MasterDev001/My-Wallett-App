package com.example.presenter.di

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import com.example.presenter.currency.CurrencyViewModelImpl
import com.example.presenter.home.HomeViewModelImpl
import com.example.presenter.signUp.SignUpViewModelImpl
import com.example.presenter.signin.SignInViewModelImpl
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
}