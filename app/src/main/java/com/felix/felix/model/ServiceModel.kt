package com.felix.felix.model

import android.util.Log
import com.felix.felix.dataClass.Service
import com.felix.felix.dataAccess.FelixDataAccess
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ServiceModel {

    init {
        var serviceRef = FelixDataAccess().getServiceRef()
        serviceRef.addValueEventListener(LoadServices())
    }

    var services = mutableListOf<Service>()
    var serviceProviders = mutableListOf<Int>()

    inner class LoadServices : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            for (serviceChild in snapshot.children) {
                for (serviceProvider in serviceChild.child("service_providers").children) {
                    serviceProviders.add(serviceProvider.value.toString().toInt())
                }
                val serviceObj = Service(
                    serviceChild.child("category").value.toString(),
                    serviceChild.child("picture").value.toString(),
                    serviceChild.child("price").value.toString(),
                    serviceChild.child("service").value.toString(),
                    serviceProviders.toMutableList()
                )
                services.add(serviceObj)
                serviceProviders.clear()
            }
            Log.i("Services", "service: $services")
        }

        override fun onCancelled(error: DatabaseError) {
            Log.i("Error", error.message)
        }
    }
}