package com.loperilla.rawg

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loperilla.rawg.coreui.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {
    private var _topBarTitle: MutableStateFlow<String> = MutableStateFlow("Rawg")
    val topBarTitle: StateFlow<String> = _topBarTitle

    fun launchNewDestination(route: Routes, newTitle: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _topBarTitle.value = if (route == Routes.HOME) {
                "Rawg"
            } else {
                newTitle
            }
        }
    }
}