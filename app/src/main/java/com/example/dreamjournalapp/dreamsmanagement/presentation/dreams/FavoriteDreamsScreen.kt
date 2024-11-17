package com.example.dreamjournalapp.dreamsmanagement.presentation.dreams

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dreamjournalapp.dreamsmanagement.presentation.dreams.components.DreamItem
import com.example.dreamjournalapp.dreamsmanagement.presentation.util.Screen


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteDreamsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: DreamsViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val expandedDreamsIds = viewModel.expandedDreamIds.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Favorite Dreams", fontWeight = FontWeight.Bold) },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "go_dream_board",
                        modifier = Modifier.clickable { navController.navigate(Screen.DreamsScreen.route) }
                    )
                    Icon(imageVector = Icons.Default.Favorite, contentDescription = "go_favorite_dreams", modifier = Modifier.clickable { navController.navigate(Screen.FavoriteDreamsScreen.route) })
                }
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(state.favoriteDreams) { dream ->
                DreamItem(
                    dream = dream,
                    modifier = Modifier
                        .fillMaxWidth(),
                    isExpanded = expandedDreamsIds.contains(dream.id),
                    onToggleExpand = {viewModel.toggleDreamExpansion(dream.id)},
                    onClickEdit = {navController.navigate(Screen.AddEditNoteScreen.route + "?dreamId=${dream.id}")},
                    addToFavorites = {if(!dream.isFavorite) viewModel.markFavorite(dream.id) else viewModel.unmarkFavorite(dream.id)},
                    isFavorite = dream.isFavorite
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

        }

    }

}