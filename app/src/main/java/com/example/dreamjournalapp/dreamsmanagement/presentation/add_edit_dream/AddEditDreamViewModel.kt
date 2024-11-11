package com.example.dreamjournalapp.dreamsmanagement.presentation.add_edit_dream

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dreamjournalapp.dreamsmanagement.data.model.DreamEntity
import com.example.dreamjournalapp.dreamsmanagement.domain.use_case.DreamUsesCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AddEditDreamViewModel @Inject constructor(
    private val dreamUsesCases: DreamUsesCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var dreamTitle = mutableStateOf("")
        private set

    var dreamDescription = mutableStateOf("")
        private set

    private var currentDreamId: Int? = null

    init {
        savedStateHandle.get<Int>("dreamId")?.let { dreamId ->
            if(dreamId != -1){
                viewModelScope.launch {
                    dreamUsesCases.getDream(dreamId)?.also { dream ->
                        currentDreamId = dream.id
                        dreamTitle.value = dream.title
                        dreamDescription.value = dream.description
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditDreamEvent){
        when(event){
            is AddEditDreamEvent.EnteredDescription -> {
                dreamDescription.value = event.value
            }
            is AddEditDreamEvent.EnteredTitle -> {
                dreamTitle.value = event.value
            }
            AddEditDreamEvent.SaveNote -> {
                viewModelScope.launch {
                    try{
                        dreamUsesCases.addDream(
                            DreamEntity(
                                id = currentDreamId,
                                title = dreamTitle.value.trim(),
                                description = dreamDescription.value.trim(),
                                date = Calendar.DATE.toString(),
                                imageUri = ""
                            )
                        )
                    } catch (e: Exception){
                        e.printStackTrace()
                    }
                }
            }
        }
    }

}