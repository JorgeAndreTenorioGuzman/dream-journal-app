package com.example.dreamjournalapp.dreamsmanagement.presentation

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dreamjournalapp.MainActivity
import com.example.dreamjournalapp.core.util.TestTags
import com.example.dreamjournalapp.di.AppModule
import com.example.dreamjournalapp.dreamsmanagement.presentation.add_edit_dream.AddEditDreamsScreen
import com.example.dreamjournalapp.dreamsmanagement.presentation.dreams.DreamsScreen
import com.example.dreamjournalapp.dreamsmanagement.presentation.dreams.FavoriteDreamsScreen
import com.example.dreamjournalapp.dreamsmanagement.presentation.util.Screen
import com.example.dreamjournalapp.ui.theme.DreamJournalAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
@UninstallModules(AppModule::class)
class DreamsEndToEndTest {


    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            DreamJournalAppTheme {
                val navController = rememberNavController()
                composeRule.activity.SetupNavGraph(navController)
            }
        }
    }

    @Test
    fun saveNewDream_editAfter() {
        composeRule.onNodeWithContentDescription("add dream").performClick()

        composeRule
            .onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
            .performTextInput("test-dream-title")
        composeRule
            .onNodeWithTag(TestTags.CONTENT_TEXT_FIELD)
            .performTextInput("test-dream-content")
        composeRule.onNodeWithContentDescription("save dream").performClick()

        composeRule.onNodeWithText("test-dream-title").assertIsDisplayed()
        composeRule.onNodeWithContentDescription("edit_dream").performClick()

        //edit dream
        composeRule
            .onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
            .assertTextContains("test-dream-title")
        composeRule
            .onNodeWithTag(TestTags.CONTENT_TEXT_FIELD)
            .assertTextContains("test-dream-content")
        composeRule
            .onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
            .performTextInput("edited-")
        composeRule.onNodeWithContentDescription("save dream").performClick()

        composeRule.onNodeWithText("edited-test-dream-title").assertIsDisplayed()

    }

}