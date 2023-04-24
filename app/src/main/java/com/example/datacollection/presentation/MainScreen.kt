package com.example.datacollection.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.datacollection.presentation.theme.DatacollectionTheme


@Composable
fun WearApp(viewModel: RoutineViewModel) {
    DatacollectionTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                viewModel.getRoutineData()
            }) {
                Text(text = "ワイワイ")
            }
        }
    }
}