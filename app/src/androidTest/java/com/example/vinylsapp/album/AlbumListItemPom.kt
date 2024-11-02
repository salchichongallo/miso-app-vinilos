package com.example.vinylsapp.album

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick

class AlbumListItemPom(private val rule: ComposeTestRule, index: Int) {
    private val titleNode = rule.onNodeWithTag("albumListItem_title_$index", useUnmergedTree = true)

    private val genreNode = rule.onNodeWithTag("albumListItem_genre_$index", useUnmergedTree = true)

    fun hasTitle(title: String): AlbumListItemPom {
        titleNode.assertTextEquals(title)
        return this
    }

    fun click(): AlbumDetailPom {
        titleNode.performClick()
        return AlbumDetailPom(rule)
    }

    fun getTitle(): String {
        val node = titleNode.fetchSemanticsNode()
        val text = node.config.getOrNull(SemanticsProperties.Text)?.firstOrNull()?.text ?: ""
        return text
    }

    fun getGenre(): String {
        val node = genreNode.fetchSemanticsNode()
        val text = node.config.getOrNull(SemanticsProperties.Text)?.firstOrNull()?.text ?: ""
        return text
    }
}
