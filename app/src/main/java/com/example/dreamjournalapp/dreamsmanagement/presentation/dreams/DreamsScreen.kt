package com.example.dreamjournalapp.dreamsmanagement.presentation.dreams

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dreamjournalapp.dreamsmanagement.presentation.dreams.components.DreamItem
import com.example.dreamjournalapp.ui.theme.DreamJournalAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DreamsScreen(modifier: Modifier = Modifier) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add dream")
            }
        },
        topBar = {
            TopAppBar(title = { Text(text = "Dream Board", fontWeight = FontWeight.Bold) } )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(10) { dream ->
                DreamItem()
                Spacer(modifier = Modifier.height(16.dp))
            }

        }

    }

}

@Preview
@Composable
private fun DreamsScreenPreview() {
    DreamJournalAppTheme {
        DreamsScreen()
    }
}