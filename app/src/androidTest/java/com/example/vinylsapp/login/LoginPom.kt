package com.example.vinylsapp.login

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick

class LoginPom(val rule: ComposeTestRule) {
    fun title() = rule.onNodeWithText("Vinilos APP")

    fun loginAsGuess() {
        rule.onNodeWithText("Ingresar como invitado").performClick()
    }
}
