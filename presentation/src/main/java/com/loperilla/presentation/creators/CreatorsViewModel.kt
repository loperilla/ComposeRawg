package com.loperilla.presentation.creators

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loperilla.rawg.domain.usecase.CreatorsUseCase
import com.loperilla.rawg.model.creator.Creator
import com.loperilla.rawg.model.domain.ResultResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CreatorsViewModel @Inject constructor(
    private val creatorsUseCase: CreatorsUseCase
) : ViewModel() {
    private var _creatorList: MutableStateFlow<List<Creator>> = MutableStateFlow(emptyList())
    val creatorList: StateFlow<List<Creator>> = _creatorList

    init {
        getAllCreators()
    }

    private fun getAllCreators() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = creatorsUseCase.getCreatorList()) {
                is ResultResponse.Error -> TODO()
                is ResultResponse.Success -> {
                    _creatorList.value = result.data.orEmpty()
                }
            }
        }
    }
}
