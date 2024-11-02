package com.example.vinylsapp.album

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.models.AlbumGenre
import com.example.vinylsapp.album.repositories.AlbumRepository
import com.example.vinylsapp.album.repositories.IAlbumRepository
import com.example.vinylsapp.album.repositories.services.RetrofitServiceFactory
import com.example.vinylsapp.album.tracks.models.Track
import com.example.vinylsapp.album.tracks.repositories.ITrackRepository
import com.example.vinylsapp.album.tracks.repositories.TrackRepository
import com.example.vinylsapp.album.tracks.repositories.services.TrackRetrofitInstance
import com.example.vinylsapp.login.LoginPom
import com.example.vinylsapp.ui.elements.RootNavigation
import org.junit.After
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

    @After
    fun sleep() {
        Thread.sleep(2000)
    }

    @Test
    fun shouldNavigateToTheSelectedAlbumDetail() {
        // Start the app
        val album = Album(
            id = 100,
            name = "3 Buscando América",
            cover = "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
            genre = AlbumGenre.SALSA,
            releaseDate = "1984-08-01T00:00:00.000Z"
        )
        val albumRepo = AlbumRepositoryMock(listOf(album))
        composeTestRule.setContent {
            RootNavigation(
                albumRepo = albumRepo,
                trackRepository = EmptyTrackRepository()
            )
        }

        login.loginAsGuess()
        val albumItem = albumList.firstAlbum()

        albumItem.hasTitle(album.name)
        albumItem.hasGenre(album.genre.value)
        val detail = albumItem.click()

        detail.screen().assertIsDisplayed()
        detail.cover().assertIsDisplayed()

        detail
            .hasTitle(album.name)
            .hasGenre(album.genre.value)
            .hasYear("1984")
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
        val album1 = albumList.firstAlbum()
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

        val album = albumList.firstAlbum()
        val detail = album.click()
        detail.screen().assertIsDisplayed()
        detail.verifyEmptyTracks()
    }
}

class AlbumRepositoryMock(private val albums: List<Album>) : IAlbumRepository {
    override suspend fun getAll(): List<Album> {
        return albums
    }

    override suspend fun getOne(albumId: Int): Album {
        val album = albums.find { it.id == albumId }
        if (album == null) {
            throw Exception("The given album id '$albumId' was not found")
        }
        return album
    }
}

class EmptyTrackRepository : ITrackRepository {
    override suspend fun getAll(albumId: Int): List<Track> {
        return listOf()
    }
}
