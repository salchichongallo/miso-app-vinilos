package com.example.vinylsapp.album

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
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
    fun navigateAlbumDetail() {
        val albumTitle = "Buscando América"
        val albumGenre = "Salsa"

        login.loginAsGuess()
        albumList.selectAlbumItem(albumTitle)

        val albumDetail = AlbumDetailPom(composeTestRule)
        albumDetail.screen().assertIsDisplayed()
        albumDetail.cover().assertIsDisplayed()
        albumDetail.title().assertIsDisplayed()
        albumDetail.title().assertIsDisplayed().assertTextEquals(albumTitle)
        albumDetail.genre().assertIsDisplayed()
        albumDetail.genre().assertTextEquals(albumGenre)
        albumDetail.year().assertIsDisplayed()


    }


}
