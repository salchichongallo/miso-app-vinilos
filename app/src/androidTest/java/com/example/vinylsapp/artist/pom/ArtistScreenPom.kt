package com.example.vinylsapp.artist.pom

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithTag

class ArtistScreenPom(val rule: ComposeTestRule) {
    fun assertArtistItemCount(expectedCount: Int) {
        rule.onAllNodesWithTag("artistItem").assertCountEquals(expectedCount)
    }
}