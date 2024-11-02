package com.example.vinylsapp.album

import androidx.compose.ui.test.junit4.ComposeTestRule

class AlbumScreenPom(val rule: ComposeTestRule) {
    fun albumAt(index: Int): AlbumListItemPom {
        return AlbumListItemPom(rule, index)
    }

    fun firstAlbum() = albumAt(index = 0)
}
