package com.felix.felix.model

class BookingUiState {
    data class Booking(
        val booking_address : String = "Address",
        val date : String = "03-01-2023",
        val time : String = "8.00",
        val travel_cost : Float = 300.00f,
        val tax : Float = 10.0f //%
    )

    data class SelectedSubService(
        val title : String = "",
        val rating : Float = 4.3f,
        val no_of_reviews : Int = 100,
        val duration : Int = 60,
        val price : Float = 0.00f,
        val description : String = "",
        val discount : Float = 0.00f, //%

    )
}

