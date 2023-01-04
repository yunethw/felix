package com.felix.felix.dataClass

data class Order(
    val customer_key : Int,
    val service_key : Int,
    val sub_service : String,
    val charges : Float,
    val date : String,
    val time : String
    )
