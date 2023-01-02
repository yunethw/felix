package com.felix.felix.viewmodel

import androidx.lifecycle.ViewModel
import com.felix.felix.model.BookingUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONObject

class BookingViewModel : ViewModel() {

    private val _bookingState = MutableStateFlow(BookingUiState.Booking())
    val bookingState: StateFlow<BookingUiState.Booking> = _bookingState.asStateFlow()

    private val _selectedSubServiceState = MutableStateFlow(BookingUiState.SelectedSubService())
    val selectedSubServiceState: StateFlow<BookingUiState.SelectedSubService> = _selectedSubServiceState.asStateFlow()

    fun selectSubService(subService : HashMap<String, JSONObject>) {

    }
}