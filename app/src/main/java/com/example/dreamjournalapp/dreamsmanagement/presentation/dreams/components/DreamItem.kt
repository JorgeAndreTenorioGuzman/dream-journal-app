package com.example.dreamjournalapp.dreamsmanagement.presentation.dreams.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dreamjournalapp.dreamsmanagement.domain.model.DreamDomainModel
import com.example.dreamjournalapp.ui.theme.DreamJournalAppTheme

@Composable
fun DreamItem(
    modifier: Modifier = Modifier,
    dream: DreamDomainModel,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit
) {


    Card(
        onClick = { onToggleExpand()},
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .padding(start = 16.dp, end= 16.dp)
            .fillMaxWidth()
            .animateContentSize(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = dream.title,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = dream.description,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Light,
                maxLines = if(isExpanded) Int.MAX_VALUE else 3
            )
        }
    }

}

@Preview
@Composable
private fun DreamItemPreview() {
    DreamJournalAppTheme {
        //DreamItem()
    }
}