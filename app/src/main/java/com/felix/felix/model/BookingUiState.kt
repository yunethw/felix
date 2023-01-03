package com.felix.felix.model

class BookingUiState {
    data class BookingState(
        val booking_address : String = "Address",
        val date : String = "03-01-2023",
        val time : String = "8.00",
        val travel_cost : Float = 300.00f,
        val tax : Float = 10.0f //%
    )

    data class SelectedSubService(
        val title : String = "",
        val imageUrl : String = "",
        val rating : String = "4.3",
        val no_of_reviews : Int = 100,
        val duration : String = "60",
        val price : String = "0.00",
        val description : String = "",
        val discount : String = "0.00" //%
    )
}

