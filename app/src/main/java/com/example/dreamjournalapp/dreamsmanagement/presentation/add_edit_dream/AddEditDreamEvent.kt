package com.example.dreamjournalapp.dreamsmanagement.presentation.add_edit_dream

sealed class AddEditDreamEvent {
    data class EnteredTitle(val value: String): AddEditDreamEvent()
    data class EnteredDescription(val value: String): AddEditDreamEvent()
    object SaveDream: AddEditDreamEvent()
}