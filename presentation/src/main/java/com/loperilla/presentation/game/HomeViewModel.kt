package com.loperilla.presentation.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.loperilla.rawg.domain.usecase.GameUseCase
import com.loperilla.rawg.domain.usecase.QueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
@OptIn(FlowPreview::class)
class HomeViewModel @Inject constructor(
    private val gameUseCase: GameUseCase,
    private val queryUseCase: QueryUseCase
) : ViewModel() {
    private var _searchInputQuery: MutableStateFlow<String> = MutableStateFlow("")
    val searchInputQuery: StateFlow<String> = _searchInputQuery
        .asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = "",
        )

    private var _searchBarActive: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val searchBarActive: StateFlow<Boolean> = _searchBarActive.asStateFlow()

    fun getPreviousQuery() = queryUseCase()
        .takeWhile { searchBarActive.value }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getAllGames() = searchInputQuery
        .debounce(300.milliseconds)
        .flatMapLatest { query ->
            gameUseCase(query).cachedIn(viewModelScope)
        }
        .takeWhile { !searchBarActive.value }

    fun searchInputChange(newValue: String) {
        viewModelScope.launch {
            _searchInputQuery.value = newValue
        }
    }

    fun changeInputActive(newValue: Boolean) {
        viewModelScope.launch {
            _searchBarActive.value = newValue
        }
    }

    fun searchGamesWithCurrentValue(querySearch: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _searchBarActive.value = false
            queryUseCase.insertNewQuery(querySearch)
        }
    }
}