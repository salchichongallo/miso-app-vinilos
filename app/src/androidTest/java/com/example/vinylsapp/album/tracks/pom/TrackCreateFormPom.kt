package com.example.vinylsapp.album.tracks.pom

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput

class TrackCreateFormPom(val rule: ComposeTestRule) {

    fun fillAndSubmitTrackForm(name: String, duration: String) {
        enterName(name)
        enterDuration(duration)
        createButton()
        closeSuccessAlert()
        goBack()
    }

    fun fillAndCancelTrackForm(name: String, duration: String) {
        enterName(name)
        enterDuration(duration)
        cancelButton()
    }

    private fun enterName(value: String) {
        val nameTrackTextField = rule.onNodeWithTag("NameTrackTextField")
        nameTrackTextField.performTextInput(value)
    }

    private fun enterDuration(value: String) {
        val durationTrackTextField = rule.onNodeWithTag("DurationTrackTextField")
        durationTrackTextField.performTextInput(value)
    }

    private fun createButton() {
        val createTrackButton = rule.onNodeWithTag("CreateTrackButton")
        createTrackButton.performClick()
    }

    private fun closeSuccessAlert() {
        val closeSuccessAlertButton = rule.onNodeWithTag("CloseSuccessAlertButton")
        closeSuccessAlertButton.performClick()
    }

    private fun goBack() {
        val goBackButton = rule.onNodeWithTag("BackButton")
        goBackButton.performClick()
    }

    private fun cancelButton() {
        val cancelButton = rule.onNodeWithTag("CancelTrackButton")
        cancelButton.performClick()
    }


}