package com.example.vinylsapp.album

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinylsapp.album.mocks.AlbumRepositoryMock
import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.models.AlbumGenre
import com.example.vinylsapp.album.pom.AlbumNewFormPom
import com.example.vinylsapp.album.pom.AlbumScreenPom
import com.example.vinylsapp.album.tracks.mock.TrackRepositoryMock
import com.example.vinylsapp.login.pom.LoginPom
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
    private lateinit var albumNewForm: AlbumNewFormPom

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        login = LoginPom(composeTestRule)
        albumList = AlbumScreenPom(composeTestRule)
        albumNewForm = AlbumNewFormPom(composeTestRule)
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
        composeTestRule.setContent {
            RootNavigation(
                albumRepo = AlbumRepositoryMock(listOf(album)),
                trackRepository = TrackRepositoryMock(mutableListOf())
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
        val albumMock1 = Album(
            id = 100,
            name = "3 Buscando América",
            cover = "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
            genre = AlbumGenre.CLASSICAL,
            releaseDate = "1984-08-01T00:00:00.000Z",
        )
        val albumMock2 = Album(
            id = 101,
            name = "Poeta del pueblo",
            cover = "https://cdn.shopify.com/s/files/1/0275/3095/products/image_4931268b-7acf-4702-9c55-b2b3a03ed999_1024x1024.jpg",
            genre = AlbumGenre.SALSA,
            releaseDate = "2024-08-01T00:00:00.000Z",
        )
        composeTestRule.setContent {
            RootNavigation(
                albumRepo = AlbumRepositoryMock(listOf(albumMock1, albumMock2)),
                trackRepository = TrackRepositoryMock(mutableListOf()),
            )
        }

        login.loginAsGuess()
        val album1 = albumList.albumAt(index = 0)
        val detail1 = album1.click()

        detail1.screen().assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Regresar").performClick()

        // Next album
        val album2 = albumList.albumAt(index = 1)
        val detail2 = album2.click()

        detail2.screen().assertIsDisplayed()
        detail2.cover().assertIsDisplayed()

        detail2
            .hasTitle(albumMock2.name)
            .hasGenre(albumMock2.genre.value)
            .hasYear("2024")
    }

    @Test
    fun selectedAlbumWithoutTracks() {
        // Start the app
        val albumMock = Album(
            id = 100,
            name = "3 Buscando América",
            cover = "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
            genre = AlbumGenre.FOLK,
            releaseDate = "1984-08-01T00:00:00.000Z"
        )
        composeTestRule.setContent {
            RootNavigation(
                albumRepo = AlbumRepositoryMock(listOf(albumMock)),
                trackRepository = TrackRepositoryMock(mutableListOf()),
            )
        }

        login.loginAsGuess()

        val album = albumList.firstAlbum()
        val detail = album.click()
        detail.screen().assertIsDisplayed()
        detail.verifyEmptyTracks()
    }

    @Test
    fun createAlbumSuccessfully() {
        val albumName = "Album 1 de prueba"
        composeTestRule.setContent {
            RootNavigation(
                albumRepo = AlbumRepositoryMock(listOf()),
                trackRepository = TrackRepositoryMock(mutableListOf())
            )
        }

        login.loginAsCollector()
        albumNewForm.clickOnCreate()
        albumNewForm.enterForm(albumName)
        albumNewForm.clickOnSave()
        composeTestRule.onNodeWithContentDescription("Regresar").performClick()

        composeTestRule.onNodeWithText(albumName).assertIsDisplayed()
    }

    @Test
    fun noCreateAlbumWithoutSave() {
        val albumName = "Album 2 de prueba"
        composeTestRule.setContent {
            RootNavigation(
                albumRepo = AlbumRepositoryMock(listOf()),
                trackRepository = TrackRepositoryMock(mutableListOf())
            )
        }

        login.loginAsCollector()
        albumNewForm.clickOnCreate()
        albumNewForm.enterForm(albumName)
        composeTestRule.onNodeWithContentDescription("Regresar").performClick()

        composeTestRule.onNodeWithText(albumName).assertDoesNotExist()
    }
}
