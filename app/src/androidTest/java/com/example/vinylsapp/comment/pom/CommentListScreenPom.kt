package com.example.vinylsapp.comment.pom

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag

class CommentListScreenPom(val rule: ComposeTestRule) {
    fun createCommentButton() = rule.onNodeWithTag("CreateCommentButton")
}