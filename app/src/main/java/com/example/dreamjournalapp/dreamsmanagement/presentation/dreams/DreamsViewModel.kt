package com.example.dreamjournalapp.dreamsmanagement.presentation.dreams

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dreamjournalapp.dreamsmanagement.data.model.DreamEntity
import com.example.dreamjournalapp.dreamsmanagement.domain.model.DreamDomainModel
import com.example.dreamjournalapp.dreamsmanagement.domain.use_case.DreamUsesCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DreamsViewModel @Inject constructor(
    private val dreamUsesCases: DreamUsesCases
): ViewModel() {

    private val _state = mutableStateOf(DreamsState())
    val state: State<DreamsState> = _state

    init {
        getDreams()
    }

    private fun getDreams() {
        dreamUsesCases.getDreams()
            .onEach { dreams ->
                _state.value = state.value.copy(
                    dreams = dreams
                )
            }.launchIn(viewModelScope)
    }

}