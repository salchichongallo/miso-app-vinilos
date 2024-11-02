package com.example.vinylsapp.album

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinylsapp.MainActivity
import com.example.vinylsapp.login.LoginPom
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumTest {

    private lateinit var login: LoginPom
    private lateinit var albumList: AlbumScreenPom

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        login = LoginPom(composeTestRule)
        albumList = AlbumScreenPom(composeTestRule)
    }

    @Test
    fun shouldNavigateToTheSelectedAlbumDetail() {
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
}
