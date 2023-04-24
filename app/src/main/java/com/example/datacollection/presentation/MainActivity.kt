package com.example.datacollection.presentation

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.datacollection.R
import com.example.datacollection.presentation.theme.DatacollectionTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.DayOfWeek
import java.time.LocalDate

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearApp("Android")
            Button(onClick = {
                getTodayRoutine()
            }) {
                Text(text = "ワイワイ")
            }
        }
    }
}

@Composable
fun WearApp(greetingName: String) {
    DatacollectionTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Center
        ) {
            Greeting(greetingName = greetingName)
        }
    }
}

@Composable
fun Greeting(greetingName: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = stringResource(R.string.hello_world, greetingName)
    )
}

fun getRoutineData(){

    val retrofit = Retrofit.Builder()
        .baseUrl("https://script.google.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiRoutine = retrofit.create(SpreadsheetRoutine::class.java)


    GlobalScope.launch {
        try {
            withContext(Dispatchers.IO){
                val response = apiRoutine.getData()
                if (response.isSuccessful) {
                    Log.d(ContentValues.TAG, "Success")
                    val data = response.body()
                    println(data)
                } else {
                    Log.d(ContentValues.TAG, "Error: ${response.code()}")
                }
            }
        }catch(e: Exception){
            println("Exception caught: ${e.message}")
        }finally {
            println("finally block executed")
        }
    }

}

fun getTodayRoutine(){
    val today: LocalDate = LocalDate.now()
    val dayOfWeek: DayOfWeek = today.dayOfWeek
    println("Today is $dayOfWeek")
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp("Preview Android")
}