package com.example.vinylsapp.album.tracks

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinylsapp.album.mocks.AlbumRepositoryMock
import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.models.AlbumGenre
import com.example.vinylsapp.album.pom.AlbumScreenPom
import com.example.vinylsapp.album.tracks.mock.TrackRepositoryMock
import com.example.vinylsapp.album.tracks.pom.TrackCreateFormPom
import com.example.vinylsapp.artist.mocks.ArtistRepositoryMock
import com.example.vinylsapp.login.pom.LoginPom
import com.example.vinylsapp.ui.elements.RootNavigation
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TrackTest {

    private lateinit var login: LoginPom
    private lateinit var albumList: AlbumScreenPom
    private lateinit var createTrack: TrackCreateFormPom

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        login = LoginPom(composeTestRule)
        albumList = AlbumScreenPom(composeTestRule)
        createTrack = TrackCreateFormPom(composeTestRule)
    }

    @After
    fun sleep() {
        Thread.sleep(2000)
    }

    @Test
    fun shouldNavigateAndCreateTrack() {
        val trackName = "Canción de prueba"
        val trackDuration = "03:30"
        val albumMock = Album(
            id = 100,
            name = "3 Buscando América",
            cover = "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
            genre = AlbumGenre.SALSA,
            releaseDate = "1984-08-01T00:00:00.000Z"
        )
        composeTestRule.setContent {
            RootNavigation(
                albumRepo = AlbumRepositoryMock(listOf(albumMock)),
                trackRepository = TrackRepositoryMock(mutableListOf()),
                artistRepository = ArtistRepositoryMock(mutableListOf())
            )
        }

        login.loginAsCollector()
        val album = albumList.firstAlbum()
        val detail = album.click()
        detail.createTrackButton().performClick()

        createTrack.fillAndSubmitTrackForm(trackName, trackDuration)
        composeTestRule.onNodeWithText(trackName).assertIsDisplayed()
    }

    @Test
    fun shouldNavigateAndCancelTrack() {
        val trackName = "Prueba 2"
        val trackDuration = "03:30"
        val albumMock = Album(
            id = 100,
            name = "3 Buscando América",
            cover = "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
            genre = AlbumGenre.ROCK,
            releaseDate = "1984-08-01T00:00:00.000Z"
        )
        composeTestRule.setContent {
            RootNavigation(
                albumRepo = AlbumRepositoryMock(listOf(albumMock)),
                trackRepository = TrackRepositoryMock(mutableListOf()),
                artistRepository = ArtistRepositoryMock(mutableListOf())
            )
        }
        login.loginAsCollector()
        val album = albumList.firstAlbum()
        val detail = album.click()
        detail.createTrackButton().performClick()
        createTrack.fillAndCancelTrackForm(trackName, trackDuration)

        composeTestRule.onNodeWithText(trackName).assertDoesNotExist()
    }
}