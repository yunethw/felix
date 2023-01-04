package com.felix.felix.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.felix.felix.model.BookingUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BookingViewModel : ViewModel() {

    private val _bookingState = MutableStateFlow(BookingUiState.BookingState())
    val bookingState: StateFlow<BookingUiState.BookingState> = _bookingState.asStateFlow()

    private val _selectedSubServiceState = MutableStateFlow(BookingUiState.SelectedSubService())
    val selectedSubServiceState: StateFlow<BookingUiState.SelectedSubService> = _selectedSubServiceState.asStateFlow()

    fun selectSubService(subService : HashMap<String, String>) {
        _selectedSubServiceState.update { initialState ->
            Log.i("SUB SERVICE", subService.toString())
            initialState.copy(
                title = subService["title"] ?: "",
                imageUrl = subService["imageUrl"] ?: "",
                rating = subService["rating"] ?: "",
                duration = subService["duration"] ?: "",
                price = subService["price"] ?: "",
                description = subService["description"] ?: ""
            )
        }
    }
}