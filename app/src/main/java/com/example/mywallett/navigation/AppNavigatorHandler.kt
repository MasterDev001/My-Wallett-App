package com.example.mywallett.navigation

import kotlinx.coroutines.flow.Flow

interface AppNavigatorHandler {
    val navigation: Flow<NavigatorArgs>
}