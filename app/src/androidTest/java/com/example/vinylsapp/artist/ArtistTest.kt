package com.example.vinylsapp.artist

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinylsapp.album.mocks.AlbumRepositoryMock
import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.models.AlbumGenre
import com.example.vinylsapp.album.tracks.mock.TrackRepositoryMock
import com.example.vinylsapp.artist.mocks.ArtistRepositoryMock
import com.example.vinylsapp.artist.models.Artist
import com.example.vinylsapp.artist.pom.ArtistDetailPom
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
    private lateinit var artistDetail: ArtistDetailPom
    private lateinit var artistMock1: Artist
    private lateinit var artistMock2: Artist

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        login = LoginPom(composeTestRule)
        artistList = ArtistScreenPom(composeTestRule)
        artistDetail = ArtistDetailPom(composeTestRule)
        artistMock1 = Artist(
            id = 100,
            name = "Bruno Mars 1",
            image = "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
            description = "Cantante de pop 1",
            birthDate = "1984-08-01T00:00:00.000Z",
            albums = listOf(
                Album(
                    id = 101,
                    name = "Poeta del pueblo 1",
                    cover = "https://cdn.shopify.com/s/files/1/0275/3095/products/image_4931268b-7acf-4702-9c55-b2b3a03ed999_1024x1024.jpg",
                    genre = AlbumGenre.SALSA,
                    releaseDate = "2024-08-01T00:00:00.000Z",
                ),
                Album(
                    id = 102,
                    name = "Poeta del pueblo 2",
                    cover = "https://cdn.shopify.com/s/files/1/0275/3095/products/image_4931268b-7acf-4702-9c55-b2b3a03ed999_1024x1024.jpg",
                    genre = AlbumGenre.ROCK,
                    releaseDate = "2024-08-01T00:00:00.000Z",
                )
            )
        )
        artistMock2 = Artist(
            id = 101,
            name = "Bruno Mars 2",
            image = "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
            description = "Cantante de pop 2",
            birthDate = "1984-08-01T00:00:00.000Z",
            albums = listOf(
                Album(
                    id = 101,
                    name = "Poeta del pueblo 1",
                    cover = "https://cdn.shopify.com/s/files/1/0275/3095/products/image_4931268b-7acf-4702-9c55-b2b3a03ed999_1024x1024.jpg",
                    genre = AlbumGenre.SALSA,
                    releaseDate = "2024-08-01T00:00:00.000Z",
                ),
                Album(
                    id = 102,
                    name = "Poeta del pueblo 2",
                    cover = "https://cdn.shopify.com/s/files/1/0275/3095/products/image_4931268b-7acf-4702-9c55-b2b3a03ed999_1024x1024.jpg",
                    genre = AlbumGenre.ROCK,
                    releaseDate = "2024-08-01T00:00:00.000Z",
                )
            )
        )
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
                trackRepository = TrackRepositoryMock(mutableListOf()),
                artistRepository = ArtistRepositoryMock(listOf())
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
        val artistListMock = listOf(artistMock1)
        composeTestRule.setContent {
            RootNavigation(
                albumRepo = AlbumRepositoryMock(listOf()),
                trackRepository = TrackRepositoryMock(mutableListOf()),
                artistRepository = ArtistRepositoryMock(artistListMock)
            )
        }

        login.loginAsGuess()
        navigationPom.navigateToArtistScreen()

        val totalArtistExpected = 1
        artistList.assertArtistItemCount(totalArtistExpected)
    }

    @Test
    fun validateDetailArtist() {
        val navigationPom = NavigationPom(composeTestRule)
        val artistListMock = listOf(artistMock1)
        composeTestRule.setContent {
            RootNavigation(
                albumRepo = AlbumRepositoryMock(listOf()),
                trackRepository = TrackRepositoryMock(mutableListOf()),
                artistRepository = ArtistRepositoryMock(artistListMock)
            )
        }

        login.loginAsCollector()
        navigationPom.navigateToArtistScreen()

        artistList.clickArtistItem(0)
        artistDetail.isDetailArtistDisplayed(artistMock1.name)

        artistDetail.hasNameArtist(artistMock1.name)


    }

    @Test
    fun shouldSelectAnotherArtist() {
        val navigationPom = NavigationPom(composeTestRule)
        val artistListMock = listOf(artistMock1, artistMock2)
        composeTestRule.setContent {
            RootNavigation(
                albumRepo = AlbumRepositoryMock(listOf()),
                trackRepository = TrackRepositoryMock(mutableListOf()),
                artistRepository = ArtistRepositoryMock(artistListMock)
            )
        }

        login.loginAsCollector()
        navigationPom.navigateToArtistScreen()

        artistList.clickArtistItem(0)
        artistDetail.isDetailArtistDisplayed(artistMock1.name)

        composeTestRule.onNodeWithContentDescription("Regresar").performClick()

        artistList.clickArtistItem(1)
        artistDetail.isDetailArtistDisplayed(artistMock2.name)
    }

    @Test
    fun shouldCancelArtistAssociation() {
        val navigationPom = NavigationPom(composeTestRule)
        val artistListMock = listOf(artistMock1)
        composeTestRule.setContent {
            RootNavigation(
                albumRepo = AlbumRepositoryMock(listOf()),
                trackRepository = TrackRepositoryMock(mutableListOf()),
                artistRepository = ArtistRepositoryMock(artistListMock)
            )
        }

        login.loginAsCollector()
        navigationPom.navigateToArtistScreen()

        artistList.clickArtistItem(0)
        artistDetail.isDetailArtistDisplayed(artistMock1.name)

        composeTestRule.onNodeWithContentDescription("Agregar artista a álbum").performClick()
        composeTestRule.onNodeWithText("Agregar artista a álbum").assertExists()
        composeTestRule.onNodeWithContentDescription("Regresar").performClick()

        artistDetail.isDetailArtistDisplayed(artistMock1.name)

    }

}