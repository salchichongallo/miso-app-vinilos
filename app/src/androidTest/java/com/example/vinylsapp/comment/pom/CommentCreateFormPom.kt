package com.example.vinylsapp.comment.pom

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput

class CommentCreateFormPom(val rule: ComposeTestRule) {
    fun commentButton() = rule.onNodeWithTag("CommentButton")
    private fun descriptionTextField() = rule.onNodeWithTag("DescriptionTextField")

    fun enterDescription(value: String) {
        descriptionTextField().performTextInput(value)
    }

    fun enterRating(rating: Int) {
        val validRating = rating.coerceIn(1, 5)
        rule.onNodeWithTag("Star${validRating - 1}").performClick()
    }
}