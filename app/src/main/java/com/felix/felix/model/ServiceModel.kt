package com.felix.felix.model

import android.util.Log
import com.felix.felix.dataAccess.FelixDataAccess
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.delay
import org.json.JSONObject
import kotlin.math.log


class ServiceModel {

    init {
        var serviceRef = FelixDataAccess().getServiceRef()
        serviceRef.addValueEventListener(LoadServices())
    }


    var servicesSnapshotValue = mutableListOf<HashMap<String, JSONObject>>()

    inner class LoadServices : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            servicesSnapshotValue = snapshot.value as MutableList<HashMap<String, JSONObject>>
        }

        override fun onCancelled(error: DatabaseError) {
            Log.i("Error", error.message)
        }
    }

    var servicesByCategory = mutableListOf<HashMap<String, JSONObject>>()

    suspend fun getServicesForCategory(category: String): MutableList<HashMap<String, JSONObject>> {
        delay(1L)

        servicesSnapshotValue.filterNotNull().forEach { service ->
            if (service["category"].toString().equals(category)) {
                servicesByCategory.add(service)
            }
        }

        return servicesByCategory.toMutableList()
    }

    suspend fun getServiceByTitle(title: String): HashMap<String, *>? {
        delay(1L)
        servicesSnapshotValue.filterNotNull().forEach { service ->
            if (service["title"].toString().equals(title)) {
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


    private var servicesWithSubServices =
        mutableListOf<HashMap<String, HashMap<String, JSONObject>>>()

    private suspend fun getServicesWithSubServices(): MutableList<HashMap<String, HashMap<String, JSONObject>>> {
        delay(2000L)
        servicesSnapshotValue.filterNotNull().forEach { service ->
            if (service["sub-services"] != null) {
                servicesWithSubServices.add(service as HashMap<String, HashMap<String, JSONObject>>)
            }
        }
        return servicesWithSubServices
    }

    var randomSingleSubServices = mutableListOf<HashMap<String, HashMap<String, JSONObject>>>()

    suspend fun getSubServicesFrontPage(): List<HashMap<String, HashMap<String, JSONObject>>> {
        delay(1000L)

        getServicesWithSubServices().forEach { service ->
            var subServicesAll =
                service["sub-services"] as HashMap<String, HashMap<String, JSONObject>>

            var keyList = mutableListOf<String>()

            subServicesAll.forEach { (s, _) ->
                keyList.add(s)
            }

            var randomKey = keyList.random()

            var randomSubService = subServicesAll.getValue(randomKey)

            subServicesAll.clear()

            subServicesAll[randomKey] = randomSubService

            service.replace("sub-services", subServicesAll as java.util.HashMap<String, JSONObject>)

            //                var finalServices = mutableListOf<HashMap<String,HashMap<String,*>>>()
            //
            //                var keyFilterList = mutableListOf<String>()
            //                service.forEach { (s, hashMap) ->
            //                    if(!keyFilterList.contains(hashMap.keys.toString())){
            //                        keyFilterList.add(hashMap.keys.toString())
            //                        finalServices.add(service)
            //                    }
            //                }
            //                Log.i("Rand SS",keyFilterList.toString())

            randomSingleSubServices.add(service)

            //                Log.i("Rand SS", service.toString())

        }
        Log.i("SERVICE_MODEL", "Data received")

        return randomSingleSubServices.toList()
    }

    suspend fun getData(): MutableList<HashMap<String, JSONObject>> {
        delay(1L)
//        Log.i("LIST", "$servicesSnapshotValue")
        return servicesSnapshotValue.toMutableList()
    }
}
