package com.felix.felix.dataAccess

import com.google.firebase.database.*

class FelixDataAccess {
    
    fun getServiceRef(): DatabaseReference {
        var mydatabase = FirebaseDatabase.getInstance()
        return mydatabase.getReference("services")
    }

    fun getAllRef(): DatabaseReference{
        var mydatabase = FirebaseDatabase.getInstance()
        return mydatabase.reference
    }

    fun getCategoryRef() : DatabaseReference{
        var mydatabase = FirebaseDatabase.getInstance()
        return mydatabase.getReference("categories")
    }

    fun getCustomerRef(): DatabaseReference {
        var mydatabase = FirebaseDatabase.getInstance()
        return mydatabase.getReference("customers")
    }

}

