package com.example.datacollection.presentation

import retrofit2.Response
import retrofit2.http.GET

interface SpreadsheetRoutine {

    @GET("macros/s/AKfycbyLfJadN7-cg2iYkVx9oAsY02GHRVQQa7h0kYY0tOrdAUF3yFFaBHCWY8cxNiy5IBKk3g/exec")
    suspend fun getData(): Response<List<Map<String, Any>>>
}