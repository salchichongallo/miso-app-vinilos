package com.example.vinylsapp.album

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.vinylsapp.MainActivity

class AlbumScreenPom(val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>) {
    fun selectAlbumItem(albumTitle: String) {
        rule.onNodeWithText(albumTitle).performClick()
    }
}