package com.vanyscore.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanyscore.app.data.IAppStorage
import com.vanyscore.app.theme.AppTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

data class AppState(
    val date: Date,
    val theme: AppTheme = AppTheme.YELLOW_LIGHT,
)

@HiltViewModel
class AppViewModel @Inject constructor(
    private val appStorage: IAppStorage
) : ViewModel() {

    private val _state = MutableStateFlow(AppState(date = Date(), theme = appStorage.getThemeType()))
    val state = _state.asStateFlow()

    fun setTheme(appTheme: AppTheme) {
        viewModelScope.launch {
            appStorage.setThemeType(appTheme)
            _state.update {
                it.copy(theme = appTheme)
            }
        }
    }

    fun updateDate(date: Date) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    date = date
                )
            }
        }
    }
}