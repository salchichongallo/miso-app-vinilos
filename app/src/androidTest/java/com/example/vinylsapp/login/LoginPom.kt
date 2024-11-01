package com.example.vinylsapp.login

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.vinylsapp.MainActivity

class LoginPom(val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>) {
    fun title() = rule.onNodeWithText("Vinilos APP")

    fun loginAsGuess() {
        rule.onNodeWithText("Ingresar como invitado").performClick()
    }
}
