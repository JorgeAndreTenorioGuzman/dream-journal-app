package com.example.dreamjournalapp.dreamsmanagement.presentation.dreams

import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dreamjournalapp.MainActivity
import com.example.dreamjournalapp.di.AppModule
import com.example.dreamjournalapp.dreamsmanagement.data.repository.FakeDreamRepositoryImpl
import com.example.dreamjournalapp.dreamsmanagement.domain.model.DreamDomainModel
import com.example.dreamjournalapp.dreamsmanagement.domain.use_case.AddDreamToFavourites
import com.example.dreamjournalapp.dreamsmanagement.domain.use_case.AddDreamUseCase
import com.example.dreamjournalapp.dreamsmanagement.domain.use_case.DreamUsesCases
import com.example.dreamjournalapp.dreamsmanagement.domain.use_case.GetDreamUseCase
import com.example.dreamjournalapp.dreamsmanagement.domain.use_case.GetDreamsUseCase
import com.example.dreamjournalapp.dreamsmanagement.domain.use_case.GetFavoriteDreams
import com.example.dreamjournalapp.dreamsmanagement.presentation.util.Screen
import com.example.dreamjournalapp.ui.theme.DreamJournalAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.ZonedDateTime

@HiltAndroidTest
@UninstallModules(AppModule::class)
class DreamsScreenKtTest {


    @get:Rule (order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule (order = 1)
    val composeRule = createComposeRule()

    private lateinit var fakeDreamRepositoryImpl: FakeDreamRepositoryImpl
    private lateinit var testViewModel: DreamsViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        // Initialize the fake repository and use cases
        fakeDreamRepositoryImpl = FakeDreamRepositoryImpl()

        runBlocking {
            fakeDreamRepositoryImpl.insertDream(
                DreamDomainModel(
                    id = 1,
                    title = "Dream 1",
                    description = "Dream 1 description",
                    isFavorite = false,
                    creationTime = ZonedDateTime.now(),
                    imageUri = ""
                )
            )
            fakeDreamRepositoryImpl.insertDream(
                DreamDomainModel(
                    id = 2,
                    title = "Dream 2",
                    description = "Dream 2 description",
                    isFavorite = true,
                    creationTime = ZonedDateTime.now(),
                    imageUri = ""
                )
            )
        }

        val testUsesCases = DreamUsesCases(
            getDreams = GetDreamsUseCase(fakeDreamRepositoryImpl),
            addDream = AddDreamUseCase(fakeDreamRepositoryImpl),
            getDream = GetDreamUseCase(fakeDreamRepositoryImpl),
            addDreamToFavourites = AddDreamToFavourites(fakeDreamRepositoryImpl),
            getFavoriteDreams = GetFavoriteDreams(fakeDreamRepositoryImpl)
        )

        testViewModel = DreamsViewModel(testUsesCases)

        // Set the content
        composeRule.setContent {
            DreamJournalAppTheme {
                val navController = rememberNavController()
                DreamsScreen(viewModel = testViewModel, navController = navController)
            }
        }
    }

    @Test
    fun testDreamExpansion() {


        // Wait for the content to load
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithText("Dream 1").fetchSemanticsNodes().isNotEmpty()
        }

        // Assert that dreams are displayed
        composeRule.onNodeWithText("Dream 1").assertIsDisplayed()
        composeRule.onNodeWithText("Dream 2").assertIsDisplayed()

        // Simulate expanding the first dream
        composeRule.onNodeWithText("Dream 1").performClick()

        // Verify the state of expandedDreamIds contains the first dream ID
        composeRule.runOnIdle {
            assertTrue(testViewModel.expandedDreamIds.value.contains(1))
        }

        // Simulate collapsing the first dream
        composeRule.onNodeWithText("Dream 1").performClick()

        // Verify the state of expandedDreamIds is empty
        composeRule.runOnIdle {
            assertTrue(testViewModel.expandedDreamIds.value.isEmpty())
        }
    }






}