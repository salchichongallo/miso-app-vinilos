package com.example.vinylsapp.shared.pom

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick

class NavigationPom(private val rule: ComposeTestRule) {
    fun navigateToArtistScreen() {
        val artistButton = rule.onNodeWithText("Artistas")
        artistButton.performClick()
    }
}