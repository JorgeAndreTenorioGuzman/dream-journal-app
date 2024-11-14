package com.example.dreamjournalapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dreamjournalapp.dreamsmanagement.presentation.add_edit_dream.AddEditDreamsScreen
import com.example.dreamjournalapp.dreamsmanagement.presentation.dreams.DreamsScreen
import com.example.dreamjournalapp.dreamsmanagement.presentation.dreams.FavoriteDreamsScreen
import com.example.dreamjournalapp.dreamsmanagement.presentation.util.Screen
import com.example.dreamjournalapp.ui.theme.DreamJournalAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DreamJournalAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.DreamsScreen.route
                ) {
                    composable(route = Screen.DreamsScreen.route){
                        DreamsScreen(navController = navController)
                    }
                    composable(route = Screen.FavoriteDreamsScreen.route){
                        FavoriteDreamsScreen(navController = navController)
                    }
                    composable(
                        route = Screen.AddEditNoteScreen.route +
                                "?dreamId={dreamId}",
                        arguments = listOf(
                            navArgument(
                                name = "dreamId"
                            ){
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        AddEditDreamsScreen(navController = navController)
                    }
                }
            }
        }
    }
}


