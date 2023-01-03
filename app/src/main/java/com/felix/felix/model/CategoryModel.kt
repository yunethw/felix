package com.felix.felix.model

import android.util.Log
import com.felix.felix.dataAccess.FelixDataAccess
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.delay
import org.json.JSONObject

class CategoryModel {

    init {
        var categoryRef = FelixDataAccess().getCategoryRef()
        categoryRef.addValueEventListener(LoadCategories())
    }

    private var categoriesSnapshot = mutableListOf<HashMap<String,JSONObject>>()

    private inner class LoadCategories : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
//            Log.i("CATEGORIES",snapshot.value.toString())
            categoriesSnapshot = snapshot.value as MutableList<HashMap<String, JSONObject>>
//            Log.i("CATEGORY LIST", categoriesSnapshot.toString())
//            print(categoriesSnapshot.toString())
        }

        override fun onCancelled(error: DatabaseError) {
            Log.i("Error", error.message)
        }
    }

    suspend fun getData(): List<HashMap<String, JSONObject>> {
        delay(1000L)
        //categoriesSnapshot.filterNotNull()
//        Log.i("LISTC","$categoriesSnapshot")
        return categoriesSnapshot.filterNotNull().toList()
    }
}