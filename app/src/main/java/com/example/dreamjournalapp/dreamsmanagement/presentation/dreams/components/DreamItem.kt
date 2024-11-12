package com.example.dreamjournalapp.dreamsmanagement.presentation.dreams.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dreamjournalapp.ui.theme.DreamJournalAppTheme

@Composable
fun DreamItem(modifier: Modifier = Modifier) {
    
    Card(
        onClick = { /*TODO*/ },
        elevation = CardDefaults.cardElevation(),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Became the King of Albion",
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Yesterdays dream was awesome, I became the king of albion, it was amazing. " +
                        "I fought in the name of my country and saved the world as a hero and after i became the king" +
                        " it wa an amazing dream. i was a good king and my rule was just and wise, and innovative and filled with joy.",
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Light
            )
        }
    }

}

@Preview
@Composable
private fun DreamItemPreview() {
    DreamJournalAppTheme {
        DreamItem()
    }
}