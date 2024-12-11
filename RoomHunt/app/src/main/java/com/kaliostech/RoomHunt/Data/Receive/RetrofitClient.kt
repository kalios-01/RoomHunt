package com.kaliostech.RoomHunt.Data.Receive

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://script.google.com/macros/s/AKfycbxxEQL5UPy6cDthcK1JkWL0WeJ8nzgjLLizLZMfjAecGed9HRNzRZXHwbENMzfb4X79/"

    val api: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
