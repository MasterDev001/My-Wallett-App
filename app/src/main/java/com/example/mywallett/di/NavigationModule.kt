package com.example.mywallett.di

import com.example.mywallett.navigation.AppNavigator
import com.example.mywallett.navigation.AppNavigatorDispatcher
import com.example.mywallett.navigation.AppNavigatorHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun appNavigatorModule(impl: AppNavigatorDispatcher): AppNavigator

    @Binds
    fun appNavigatorHandlerModule(impl: AppNavigatorDispatcher): AppNavigatorHandler
}