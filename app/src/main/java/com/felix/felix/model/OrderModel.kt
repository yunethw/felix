package com.felix.felix.model

import android.util.Log
import com.felix.felix.dataAccess.FelixDataAccess
import com.felix.felix.dataClass.Order
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class OrderModel {

    var serviceKey: Int? = null

    constructor(service_title: String,customer_key: Int,sub_service: String,charges: Float,date: String,time: String) {
        var serviceRef =
            FelixDataAccess().getServiceRef().orderByChild("title").equalTo("$service_title")
        val loadServices = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                serviceKey = snapshot.children.first().key.toString().toInt()
                setOrder(customer_key,sub_service,charges,date,time)
                Log.i("SERVICE KEY", snapshot.children.first().key.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("Error", error.message)
            }
        }
        serviceRef.addValueEventListener(loadServices)
    }

    fun setOrder(customer_key: Int, sub_service: String, charges: Float, date: String, time: String) {
        val order = Order(customer_key, serviceKey as Int, sub_service, charges, date, time)
        FelixDataAccess().getOrderRef().push().setValue(order)
    }


}