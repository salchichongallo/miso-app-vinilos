package com.example.vinylsapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.vinylsapp.login.pom.LoginPom
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class VinylsAppTests {
    private lateinit var login: LoginPom

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        login = LoginPom(rule)
    }

    @Test
    fun login_works() {
        login.title().assertIsDisplayed()
        login.loginAsGuess()
    }
}
