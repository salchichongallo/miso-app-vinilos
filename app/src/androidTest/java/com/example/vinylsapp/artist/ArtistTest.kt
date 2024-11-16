package com.example.vinylsapp.artist

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinylsapp.album.mocks.AlbumRepositoryMock
import com.example.vinylsapp.album.mocks.EmptyTrackRepository
import com.example.vinylsapp.artist.mocks.ArtistRepositoryMock
import com.example.vinylsapp.artist.models.Artist
import com.example.vinylsapp.artist.pom.ArtistScreenPom
import com.example.vinylsapp.login.pom.LoginPom
import com.example.vinylsapp.shared.pom.NavigationPom
import com.example.vinylsapp.ui.elements.RootNavigation
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArtistTest {
    private lateinit var login: LoginPom
    private lateinit var artistList: ArtistScreenPom

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        login = LoginPom(composeTestRule)
        artistList = ArtistScreenPom(composeTestRule)
    }

    @After
    fun sleep() {
        Thread.sleep(2000)
    }


    @Test
    fun notArtistAvailable() {
        val navigationPom = NavigationPom(composeTestRule)
        composeTestRule.setContent {
            RootNavigation(
                albumRepo = AlbumRepositoryMock(listOf()),
                trackRepository = EmptyTrackRepository()
            )
        }

        login.loginAsGuess()
        navigationPom.navigateToArtistScreen()


        val emptyArtistMessage = composeTestRule.onNodeWithText("No hay artistas disponibles")
        emptyArtistMessage.assertExists()

    }

    @Test
    fun shouldViewOnlyOneArtist() {
        val navigationPom = NavigationPom(composeTestRule)
        val artistMock = Artist(
            id = 100,
            name = "Bruno Mars",
            image = "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
            description = "Cantante de pop",
            birthDate = "1984-08-01T00:00:00.000Z"
        )
        val artistListMock = listOf(artistMock)
        composeTestRule.setContent {
            RootNavigation(
                albumRepo = AlbumRepositoryMock(listOf()),
                trackRepository = EmptyTrackRepository(),
                artistRepository = ArtistRepositoryMock(artistListMock)
            )
        }

        login.loginAsGuess()
        navigationPom.navigateToArtistScreen()

        val totalArtistExpected = 1
        artistList.assertArtistItemCount(totalArtistExpected)
    }
}