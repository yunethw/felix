package com.felix.felix.dataAccess

import com.google.firebase.database.*

class FelixDataAccess {
    
    fun getServiceRef(): DatabaseReference {
        var mydatabase = FirebaseDatabase.getInstance()
        return mydatabase.getReference("services")
    }

}

