package com.vanyscore.app

import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Calendar
import java.util.Date

data class AppState(
    val navController: NavController? = null,
    val date: Date,
) {
    companion object {
        fun bindNavController(navController: NavController) {
            _state.update {
                _state.value.copy(
                    navController = navController
                )
            }
        }

        private var _state = MutableStateFlow(AppState(date = Calendar.getInstance().time))
        val source = _state.asStateFlow()

        fun updateState(state: AppState) {
            _state.value = state
        }
    }
}