package com.felix.felix.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felix.felix.model.CategoryModel
import com.felix.felix.model.HomeUiState
import com.felix.felix.model.ServiceModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.collections.HashMap

class HomeViewModel : ViewModel() {
    // Data source
    var categories = CategoryModel()
    var services = ServiceModel()

    // Category list state
    private val _categoryState = MutableStateFlow(HomeUiState.CategoryState())
    val categoryState: StateFlow<HomeUiState.CategoryState> = _categoryState.asStateFlow()

    // Sub Service list state
    private val _subServiceState = MutableStateFlow(HomeUiState.SubServiceState())
    val subServiceState: StateFlow<HomeUiState.SubServiceState> = _subServiceState.asStateFlow()

    init {
        viewModelScope.launch {
            initializeCategoryList(categories.getData())
            initializeSubServiceList(services.getSubServicesFrontPage())
        }
    }

    fun initializeCategoryList(list : List<HashMap<String, JSONObject>>) {

        val pairList = mutableListOf<Pair<String, String>>()

        for(item in list) {
            pairList.add(
                Pair(
                    item["name"].toString(),
                    item["image"].toString()
                )
            )
        }

        _categoryState.update { initialState ->
            initialState.copy(categoryList = pairList)
        }
    }

    fun initializeSubServiceList(list : List<HashMap<String, HashMap<String, JSONObject>>>) {

        val simpleList = mutableListOf<HashMap<String, String>>()


        for(item in list) {
            var simpleHashMap =HashMap<String, String>()
            val subServiceItem = item["sub-services"] as HashMap<String, HashMap<String, *>>
            val serviceTitle = item["title"].toString()
            val subTitle = subServiceItem.keys.elementAt(0)
            val subServiceTitle : String = "$serviceTitle $subTitle"

            simpleHashMap.put("title", subServiceTitle)
            simpleHashMap.put("duration", subServiceItem.get(subTitle)?.get("duration").toString())
            simpleHashMap.put("price", subServiceItem.get(subTitle)?.get("price").toString())
            simpleHashMap.put("rating", subServiceItem.get(subTitle)?.get("rating").toString())
            simpleHashMap.put("description", subServiceItem.get(subTitle)?.get("description").toString())
            simpleHashMap.put("imageUrl", item["picture"].toString())


            simpleList.add(simpleHashMap)
        }

        _subServiceState.update { initialState ->
            initialState.copy(subServiceList = simpleList.toList())
        }
    }
}