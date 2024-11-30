package com.example.vinylsapp.artist.pom

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithText

class ArtistDetailPom(
    val rule: ComposeTestRule,
) {
    fun hasNameArtist(value: String): ArtistDetailPom {
        rule.onNodeWithText(value).assertExists()
        return this
    }

    fun isDetailArtistDisplayed(artistName: String) {
        rule.waitUntil(5000) {
            rule.onNodeWithText(artistName).isDisplayed()
        }
    }

}