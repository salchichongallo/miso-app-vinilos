package com.example.vinylsapp.album

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText

class AlbumDetailPom(
    val rule: ComposeTestRule,
) {

    fun screen() = rule.onNodeWithTag("AlbumDetailScreen")

    fun title() = rule.onNodeWithTag("AlbumTitle")

    fun genre() = rule.onNodeWithTag("AlbumGenre")

    fun year() = rule.onNodeWithTag("AlbumYear")

    fun cover() = rule.onNodeWithTag("AlbumCover")

    fun hasTitle(value: String) {
        title().assertTextEquals(value)
    }

    fun hasGenre(value: String) {
        genre().assertTextEquals(value)
    }

    fun verifyEmptyTracks() {
        val emptyTracksMessage = rule.onNodeWithText("No existen canciones asociadas al álbum")
        emptyTracksMessage.assertIsDisplayed()
    }
}
