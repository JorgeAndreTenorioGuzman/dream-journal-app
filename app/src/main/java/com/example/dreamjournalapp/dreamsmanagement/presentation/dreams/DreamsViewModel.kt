package com.example.dreamjournalapp.dreamsmanagement.presentation.dreams

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.dreamjournalapp.dreamsmanagement.data.model.DreamEntity
import com.example.dreamjournalapp.dreamsmanagement.domain.model.DreamDomainModel
import com.example.dreamjournalapp.dreamsmanagement.domain.use_case.DreamUsesCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DreamsViewModel @Inject constructor(
    private val dreamUsesCases: DreamUsesCases
): ViewModel() {

    init {

    }



}