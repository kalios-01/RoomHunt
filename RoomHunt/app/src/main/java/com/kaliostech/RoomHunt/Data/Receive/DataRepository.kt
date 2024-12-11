package com.kaliostech.RoomHunt.Data.Receive

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DataRepository {

    private val apiService: ApiService = RetrofitClient.api.create(ApiService::class.java)

    fun fetchHouses(onResult: (List<House>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val houses = apiService.getHouseItems("read")
                withContext(Dispatchers.Main) {
                    onResult(houses)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(null)
                }
            }
        }
    }
}
