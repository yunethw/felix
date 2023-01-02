package com.felix.felix.model

import android.util.Log
import com.felix.felix.dataAccess.FelixDataAccess
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.delay
import org.json.JSONObject

class CustomerModel {

    constructor(email : String) {
        var customerRef = FelixDataAccess().getCustomerRef().orderByChild("email").equalTo(email)
        //Log.i("CUSTOMER",customerSnapshot.toString())

        customerRef.addValueEventListener(LoadCustomer())
    }

    var customerSnapshot = HashMap<String, JSONObject>()

    inner class LoadCustomer : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            customerSnapshot = snapshot.value as HashMap<String, JSONObject>
            Log.i("CUSTOMER",customerSnapshot.toString())
        }

        override fun onCancelled(error: DatabaseError) {
            Log.i("Error", error.message)
        }

        suspend fun getData(): HashMap<String, JSONObject> {
            delay(1L)
            Log.i("CUSTOMER",customerSnapshot.toString())

            return customerSnapshot
        }

    }
}