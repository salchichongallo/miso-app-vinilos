package com.example.vinylsapp.album.pom

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText

class AlbumDetailPom(
    val rule: ComposeTestRule,
) {

    fun screen() = rule.onNodeWithTag("AlbumDetailScreen")

    fun cover() = rule.onNodeWithTag("AlbumCover")

    fun commentsButton() = rule.onNodeWithTag("CommentsButton")

    fun createTrackButton() = rule.onNodeWithTag("CreateTrackButton")

    fun hasTitle(value: String): AlbumDetailPom {
        title().assertTextEquals(value)
        return this
    }

    fun hasGenre(value: String): AlbumDetailPom {
        genre().assertTextEquals(value)
        return this
    }

    fun verifyEmptyTracks() {
        val emptyTracksMessage = rule.onNodeWithText("No existen canciones asociadas al álbum")
        emptyTracksMessage.assertIsDisplayed()
    }

    fun hasYear(value: String): AlbumDetailPom {
        year().assertTextEquals(value)
        return this
    }

    private fun title() = rule.onNodeWithTag("AlbumTitle")

    private fun genre() = rule.onNodeWithTag("AlbumGenre")

    private fun year() = rule.onNodeWithTag("AlbumYear")
}
