package com.example.vinylsapp.artist.pom

import androidx.compose.ui.test.junit4.ComposeTestRule

class ArtistScreenPom(val rule: ComposeTestRule) {
    fun artistAt(index: Int): ArtistListItemPom {
        return ArtistListItemPom(rule, index)
    }

    fun firstArtist() = artistAt(index = 0)
}