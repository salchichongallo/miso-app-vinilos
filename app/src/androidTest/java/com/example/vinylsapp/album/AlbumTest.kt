package com.example.vinylsapp.album

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinylsapp.album.repositories.AlbumRepository
import com.example.vinylsapp.album.repositories.services.RetrofitServiceFactory
import com.example.vinylsapp.album.tracks.models.Track
import com.example.vinylsapp.album.tracks.repositories.ITrackRepository
import com.example.vinylsapp.album.tracks.repositories.TrackRepository
import com.example.vinylsapp.album.tracks.repositories.services.TrackRetrofitInstance
import com.example.vinylsapp.login.LoginPom
import com.example.vinylsapp.ui.elements.RootNavigation
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumTest {

    private lateinit var login: LoginPom
    private lateinit var albumList: AlbumScreenPom

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        login = LoginPom(composeTestRule)
        albumList = AlbumScreenPom(composeTestRule)
    }

    @Test
    fun shouldNavigateToTheSelectedAlbumDetail() {
        // Start the app
        composeTestRule.setContent {
            RootNavigation(
                albumRepo = AlbumRepository(serviceAdapter = RetrofitServiceFactory.makeAlbumService()),
                trackRepository = TrackRepository(serviceAdapter = TrackRetrofitInstance.makeTrackService())
            )
        }

        login.loginAsGuess()
        val album = albumList.albumAt(index = 0)

        val title = album.getTitle()
        val genre = album.getGenre()

        album.hasTitle(title)
        val detail = album.click()

        detail.screen().assertIsDisplayed()
        detail.cover().assertIsDisplayed()

        detail.title().assertIsDisplayed()
        detail.hasTitle(title)

        detail.genre().assertIsDisplayed()
        detail.hasGenre(genre)

        detail.year().assertIsDisplayed()
    }

    @Test
    fun shouldSelectAnotherAlbum() {
        // Start the app
        composeTestRule.setContent {
            RootNavigation(
                albumRepo = AlbumRepository(serviceAdapter = RetrofitServiceFactory.makeAlbumService()),
                trackRepository = TrackRepository(serviceAdapter = TrackRetrofitInstance.makeTrackService())
            )
        }

        login.loginAsGuess()
        val album1 = albumList.albumAt(index = 0)
        val detail1 = album1.click()

        detail1.screen().assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Regresar").performClick()

        val album2 = albumList.albumAt(index = 1)
        val detail2 = album2.click()

        detail2.screen().assertIsDisplayed()
        detail2.cover().assertIsDisplayed()
        detail2.title().assertIsDisplayed()
        detail2.genre().assertIsDisplayed()
        detail2.year().assertIsDisplayed()
    }

    @Test
    fun selectedAlbumWithoutTracks() {
        // Start the app
        composeTestRule.setContent {
            RootNavigation(
                albumRepo = AlbumRepository(serviceAdapter = RetrofitServiceFactory.makeAlbumService()),
                trackRepository = EmptyTrackRepository(),
            )
        }

        login.loginAsGuess()

        val album = albumList.albumAt(index = 0)
        val detail = album.click()
        detail.screen().assertIsDisplayed()
        detail.verifyEmptyTracks()
    }
}

class EmptyTrackRepository : ITrackRepository {
    override suspend fun getAll(albumId: Int): List<Track> {
        return listOf()
    }
}
