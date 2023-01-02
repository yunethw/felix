package com.felix.felix.model

import org.json.JSONObject

class HomeUiState{
    data class CategoryState(
        //Pair<CategoryName, ImageUrl>
        val categoryList: List<Pair<String, String>> = listOf(
            Pair("",""), Pair("",""), Pair("",""), Pair("",""), Pair("",""), Pair("","")
        )
    )

    data class SubServiceState(
        val subServiceList: List<HashMap<String, HashMap<String, JSONObject>>>? = null
    )
}