package com.example.dreamjournalapp.dreamsmanagement.presentation.add_edit_dream


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dreamjournalapp.ui.theme.DreamJournalAppTheme

@Composable
fun AddEditDreamsScreen(modifier: Modifier = Modifier) {

    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
               Icon(imageVector = Icons.Default.Check, contentDescription = "save dream")
            }
        },
    ){ paddingValues ->

        Box(modifier = Modifier.padding(paddingValues)) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                Icon(imageVector = Icons.Default.Close, contentDescription = "close")
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "What were you dreaming about?",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .width(256.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                AddEditDreamsTextField(
                    value = "",
                    label ="Add Title",
                    placeholder = "write title",
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(24.dp))
                AddEditDreamsTextField(
                    value = "",
                    label ="Add Description",
                    placeholder = "write description",
                    height = 250.dp
                )
            }
        }

    }



}

@Composable
fun AddEditDreamsTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    placeholder: String,
    height: Dp = 64.dp,
    singleLine: Boolean = false
) {
    TextField(
        value = value,
        onValueChange = {},
        label = {Text(text = label)},
        placeholder = { Text(text = placeholder)},
        modifier = modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Gray, shape = MaterialTheme.shapes.small)
            .height(height),
        shape = MaterialTheme.shapes.small,
       singleLine = singleLine
    )
}

@Preview
@Composable
private fun AddEdditDreamsTextFieldPreview() {
    DreamJournalAppTheme {
       // AddEdditDreamsTextField()
    }
}



@Preview
@Composable
private fun AddEditDreamsScreenPreview() {
    DreamJournalAppTheme {
        AddEditDreamsScreen()
    }
}