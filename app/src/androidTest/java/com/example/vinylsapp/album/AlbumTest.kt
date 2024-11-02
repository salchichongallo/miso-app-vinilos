package com.example.vinylsapp.album

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
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
}
