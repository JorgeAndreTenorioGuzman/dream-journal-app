package com.example.dreamjournalapp.dreamsmanagement.presentation.util

sealed class Screen(val route: String){
    object DreamsScreen: Screen("dreams_screen")
    object AddEditNoteScreen: Screen("add_edit_dream_scream")
}