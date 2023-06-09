package com.loperilla.presentation.genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loperilla.rawg.domain.usecase.GenreUseCase
import com.loperilla.rawg.model.game.detail.Genre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    private val genreUseCase: GenreUseCase
) : ViewModel(){
    private var _genreList: MutableStateFlow<List<Genre>> = MutableStateFlow(emptyList())
    private val genreList: StateFlow<List<Genre>> = _genreList.asStateFlow()
    init {
        getGenreList()
    }

    private fun getGenreList() {
        viewModelScope.launch(Dispatchers.IO) {
            genreUseCase().collect {
                _genreList.value = it
            }
        }
    }
}