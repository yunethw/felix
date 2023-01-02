package com.felix.felix.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felix.felix.model.CategoryModel
import com.felix.felix.model.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.util.HashMap

class HomeViewModel : ViewModel() {
    //Data sources
    var categories = CategoryModel()

    // Category list state
    private val _categoryState = MutableStateFlow(HomeUiState.CategoryState())
    val categoryState: StateFlow<HomeUiState.CategoryState> = _categoryState.asStateFlow()

    init {
        viewModelScope.launch {
            initializeCategoryList(categories.getData())
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

        Log.i("State Update", categoryState.value.categoryList.toString())
    }
}