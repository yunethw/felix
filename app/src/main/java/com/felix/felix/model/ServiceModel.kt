package com.felix.felix.model

import android.util.Log
import com.felix.felix.dataAccess.FelixDataAccess
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.delay
import org.json.JSONObject


class ServiceModel {

    init {
        var serviceRef = FelixDataAccess().getServiceRef()
        serviceRef.addValueEventListener(LoadServices())
    }


     var servicesSnapshotValue = mutableListOf <HashMap<String,JSONObject>>()

    inner class LoadServices : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            servicesSnapshotValue = snapshot.value as MutableList<HashMap<String, JSONObject>>
        }

        override fun onCancelled(error: DatabaseError) {
            Log.i("Error", error.message)
        }
    }

    var servicesByCategory = mutableListOf<HashMap<String,JSONObject>>()

    suspend fun getServicesForCategory(category: String): MutableList<HashMap<String, JSONObject>> {
        delay(1L)

        servicesSnapshotValue.filterNotNull().forEach{ service ->
            if(service["category"].toString().equals(category)){
                servicesByCategory.add(service)
            }
        }
        return servicesByCategory.toMutableList()
    }

    suspend fun getServiceByTitle(title:String): HashMap<String, *>? {
        delay(1L)
        servicesSnapshotValue.filterNotNull().forEach{ service ->
            if(service["title"].toString().equals(title)){
                return service
            }
        }
        return null
    }

    //        suspend fun getServiceByTitle(title:String): HashMap<String, *>? {
    //            delay(1L)
    //            servicesSnapshotValue.filterNotNull().forEach{ service ->
    //                if(service["title"].toString().equals("Air Conditioner")){
    //                    return service.get("sub-services") as HashMap<String,*>
    //                }
    //            }
    //            return null
    //        }

    //        suspend fun getSubOBJByTitle(title:String){
    //            delay(1L)
    //            servicesSnapshotValue.filterNotNull().forEach { service ->
    //                if ()
    //            }
    //        }

    suspend fun getData(): MutableList<HashMap<String, JSONObject>> {
        delay(1L)
        Log.i("LIST","$servicesSnapshotValue")
        return servicesSnapshotValue.toMutableList()
    }
}