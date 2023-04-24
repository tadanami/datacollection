package com.example.datacollection.presentation

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.DayOfWeek
import java.time.LocalDate

class RoutineViewModel: ViewModel() {
    private val _routineData = MutableLiveData<List<Map<String, Any>>?>()
    val routineData: LiveData<List<Map<String, Any>>?>
        get() = _routineData

    /*
    google spread sheetからルーティンを取得する
     */
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
                        _routineData.postValue(data)
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


}