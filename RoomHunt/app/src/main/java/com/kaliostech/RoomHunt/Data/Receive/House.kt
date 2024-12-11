package com.kaliostech.RoomHunt.Data.Receive

data class House(
    val houseType: String,
    val imageUrl: String,
    val sliderImageUrls: List<String>,
    val rent:String,
    val deposit:String,
    val address :String,
    val bhk_number:String,
    val houseStatus: String
)


