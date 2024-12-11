package com.kaliostech.RoomHunt.Data.Receive


import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("exec?")
    suspend fun getHouseItems(@Query("action") action: String = "read"): List<House>
}

