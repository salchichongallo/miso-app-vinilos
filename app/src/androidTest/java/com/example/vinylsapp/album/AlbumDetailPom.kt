package com.example.vinylsapp.album

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag

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
}
