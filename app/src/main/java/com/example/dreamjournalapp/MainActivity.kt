package com.example.dreamjournalapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
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
                // Use default or injected content
                SetupNavGraph()
            }
        }
    }

    @Composable
    fun SetupNavGraph(
        navController: NavHostController = rememberNavController(),
        startDestination: String = Screen.DreamsScreen.route
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable(route = Screen.DreamsScreen.route) {
                DreamsScreen(navController = navController)
            }
            composable(route = Screen.FavoriteDreamsScreen.route) {
                FavoriteDreamsScreen(navController = navController)
            }
            composable(
                route = Screen.AddEditNoteScreen.route + "?dreamId={dreamId}",
                arguments = listOf(
                    navArgument("dreamId") {
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
