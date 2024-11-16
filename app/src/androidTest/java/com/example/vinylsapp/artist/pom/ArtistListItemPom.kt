package com.example.vinylsapp.artist.pom

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag

class ArtistListItemPom(private val rule: ComposeTestRule, index: Int) {
    private val nameNode = rule.onNodeWithTag("artistListItem_title_$index", useUnmergedTree = true)


    fun hasName(title: String): ArtistListItemPom {
        nameNode.assertTextEquals(title)
        return this
    }
}