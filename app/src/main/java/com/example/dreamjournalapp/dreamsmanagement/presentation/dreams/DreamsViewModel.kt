package com.example.dreamjournalapp.dreamsmanagement.presentation.dreams

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dreamjournalapp.dreamsmanagement.domain.use_case.DreamUsesCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DreamsViewModel @Inject constructor(
    private val dreamUsesCases: DreamUsesCases
): ViewModel() {

    private val _state = mutableStateOf(DreamsState())
    val state: State<DreamsState> = _state


    private val _expandedDreamIds = MutableStateFlow<Set<Int>>(emptySet())
    val expandedDreamIds: StateFlow<Set<Int>> = _expandedDreamIds

    init {
        getDreams()
        getFavoriteDreams()
    }

    fun toggleDreamExpansion(dreamId: Int?) {
        _expandedDreamIds.value = if (_expandedDreamIds.value.contains(dreamId)) {
            emptySet() // Collapse all if the currently expanded item is clicked
        } else {
            setOfNotNull(dreamId) // Expand only the clicked item
        }
    }

     fun markFavorite(dreamId: Int?){
         viewModelScope.launch {
             if (dreamId != null) {
                 dreamUsesCases.addDreamToFavourites(dreamId,true)
             }
         }
     }

    fun unmarkFavorite(dreamId: Int?){
        viewModelScope.launch {
            if (dreamId != null) {
                dreamUsesCases.addDreamToFavourites(dreamId,false)
            }
        }
    }

    private fun getFavoriteDreams() {
        dreamUsesCases.getFavoriteDreams()
            .onEach { dreams ->
                _state.value = state.value.copy(
                    favoriteDreams = dreams
                )
            }.launchIn(viewModelScope)
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