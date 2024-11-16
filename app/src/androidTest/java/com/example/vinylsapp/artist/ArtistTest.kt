package com.example.vinylsapp.artist

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinylsapp.album.mocks.AlbumRepositoryMock
import com.example.vinylsapp.album.mocks.EmptyTrackRepository
import com.example.vinylsapp.artist.models.Artist
import com.example.vinylsapp.login.pom.LoginPom
import com.example.vinylsapp.ui.elements.RootNavigation
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArtistTest {
    private lateinit var login: LoginPom

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        login = LoginPom(composeTestRule)
    }

    @After
    fun sleep() {
        Thread.sleep(5000)
    }


    @Test
    fun notArtistAvailable() {
        val artist = listOf<Artist>()

        composeTestRule.setContent {
            RootNavigation(
                albumRepo = AlbumRepositoryMock(listOf()),
                trackRepository = EmptyTrackRepository()
            )
        }

        login.loginAsGuess()
        composeTestRule.onNodeWithText("Artistas").performClick()

    }
}