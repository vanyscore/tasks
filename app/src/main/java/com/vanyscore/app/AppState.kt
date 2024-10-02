package com.vanyscore.app

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Calendar
import java.util.Date

data class AppState(
    val date: Date
) {
    companion object {
        private var _state = MutableStateFlow(AppState(Calendar.getInstance().time))
        val source = _state.asStateFlow()

        fun updateState(state: AppState) {
            _state.value = state
        }
    }
}