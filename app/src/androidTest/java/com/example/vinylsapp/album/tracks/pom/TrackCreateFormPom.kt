package com.example.vinylsapp.album.tracks.pom

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput

class TrackCreateFormPom(val rule: ComposeTestRule) {

    fun enterName(value: String) {
        val nameTrackTextField = rule.onNodeWithTag("NameTrackTextField")
        nameTrackTextField.performTextInput(value)
    }

    fun enterDuration(value: String) {
        val durationTrackTextField = rule.onNodeWithTag("DurationTrackTextField")
        durationTrackTextField.performTextInput(value)
    }

    fun createButton() {
        val createTrackButton = rule.onNodeWithTag("CreateTrackButton")
        createTrackButton.performClick()
    }

    fun closeSuccessAlert() {
        val closeSuccessAlertButton = rule.onNodeWithTag("CloseSuccessAlertButton")
        closeSuccessAlertButton.performClick()
    }

    fun goBack() {
        val goBackButton = rule.onNodeWithTag("BackButton")
        goBackButton.performClick()
    }


}