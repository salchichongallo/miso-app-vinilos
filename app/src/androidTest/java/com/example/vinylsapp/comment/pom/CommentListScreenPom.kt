package com.example.vinylsapp.comment.pom

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick

class CommentListScreenPom(val rule: ComposeTestRule) {
    fun openCommentCreateModal() {
        createCommentButton().performClick()
    }
    private fun createCommentButton() = rule.onNodeWithTag("CreateCommentButton")
}