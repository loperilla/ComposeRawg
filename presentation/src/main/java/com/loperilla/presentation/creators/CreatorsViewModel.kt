package com.loperilla.presentation.creators

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.loperilla.rawg.domain.usecase.CreatorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CreatorsViewModel @Inject constructor(
    private val creatorsUseCase: CreatorsUseCase
) : ViewModel() {

    fun getPagingCreators() = creatorsUseCase
        .getCreatorList()
        .cachedIn(viewModelScope)
}
