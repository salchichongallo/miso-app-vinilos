package com.example.vinylsapp.comment.pom

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag

class CommentNewSuccessAlertPom(val rule: ComposeTestRule) {
    fun dismissButton() = rule.onNodeWithTag("DismissButton")
}