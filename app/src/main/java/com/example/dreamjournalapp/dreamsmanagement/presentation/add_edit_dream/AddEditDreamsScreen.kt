package com.example.dreamjournalapp.dreamsmanagement.presentation.add_edit_dream


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dreamjournalapp.dreamsmanagement.presentation.util.Screen
import com.example.dreamjournalapp.ui.theme.DreamJournalAppTheme

@Composable
fun AddEditDreamsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AddEditDreamViewModel = hiltViewModel()
) {

    val titleState = viewModel.dreamTitle.value
    val descriptionState = viewModel.dreamDescription.value
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditDreamEvent.SaveDream)
                    navController.navigate(Screen.DreamsScreen.route)
                }
            ) {
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

                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "close",
                    modifier = Modifier.clickable { }
                )

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
                    value = titleState,
                    label ="Add Title",
                    singleLine = true,
                    onValueChange = {
                        viewModel.onEvent(AddEditDreamEvent.EnteredTitle(it))
                    }
                )
                Spacer(modifier = Modifier.height(24.dp))
                AddEditDreamsTextField(
                    value = descriptionState,
                    label ="Add Description",
                    height = 250.dp,
                    onValueChange = {
                        viewModel.onEvent(AddEditDreamEvent.EnteredDescription(it))
                    }
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
    height: Dp = 64.dp,
    singleLine: Boolean = false,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {Text(text = label)},
        modifier = modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.LightGray, shape = MaterialTheme.shapes.small)
            .height(height),
        shape = MaterialTheme.shapes.small,
        singleLine = singleLine,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        )
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
        //AddEditDreamsScreen()
    }
}