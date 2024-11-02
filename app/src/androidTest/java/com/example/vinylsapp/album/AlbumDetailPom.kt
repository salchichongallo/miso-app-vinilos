package com.example.vinylsapp.album

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.vinylsapp.MainActivity

class AlbumDetailPom(
    val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) {

    fun screen() = rule.onNodeWithTag("AlbumDetailScreen")

    fun title() = rule.onNodeWithTag("AlbumTitle")

    fun genre() = rule.onNodeWithTag("AlbumGenre")

    fun year() = rule.onNodeWithTag("AlbumYear")

    fun cover() = rule.onNodeWithTag("AlbumCover")


}