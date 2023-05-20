package com.example.mywallett.di

import com.example.mywallett.app.screens.currencies.CurrencyDirectionImpl
import com.example.mywallett.app.screens.home.HomeDirectionImpl
import com.example.mywallett.app.screens.register.RegisterDirectionImpl
import com.example.mywallett.app.screens.signin.SignInDirectionImpl
import com.example.presenter.currency.CurrencyDirection
import com.example.presenter.home.HomeDirection
import com.example.presenter.signUp.SignUpDirection
import com.example.presenter.signin.SignInDirection
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
    fun signUpDirection(impl: RegisterDirectionImpl): SignUpDirection

    @Binds
    fun homeDirection(impl: HomeDirectionImpl): HomeDirection

    @Binds
    fun currencyDirection(impl: CurrencyDirectionImpl): CurrencyDirection
}