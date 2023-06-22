package com.example.mywallett.app.screens.personHistory

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.androidx.AndroidScreen
import com.example.a_common.data.PersonData

class PersonHistoryScreen(val personData: PersonData) : AndroidScreen() {

    @Composable
    override fun Content() {

        PersonHistoryContent()
    }

    @Composable
    private fun PersonHistoryContent(

    ) {

    }
}