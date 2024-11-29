package com.example.vinylsapp.album.pom

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AlbumNewFormPom(
    val rule: ComposeTestRule,
) {

    fun clickOnCreate() {
        rule.onNodeWithContentDescription("Agregar álbum").performClick()
        rule.onNodeWithText("Crear nuevo álbum").assertExists()
    }

    private fun enterAlbumName(albumName: String) {
        rule.onNodeWithText("Nombre").performTextInput(albumName)
    }

    private fun enterCover() {
        rule.onNodeWithText("Cover")
            .performTextInput("https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg")
    }

    private fun enterDescription() {
        rule.onNodeWithText("Descripción").performTextInput("Album de Soda Stereo")
    }

    private fun enterGenero() {
        rule.onNodeWithText("Género").performClick()
        rule.onNodeWithText("Folk").performClick()
    }

    private fun enterDiscografica() {
        rule.onNodeWithText("Sello discográfico").performClick()
        rule.onNodeWithText("Elektra").performClick()
    }

    private fun enterReleaseDate() {
        val nextDate = getNextDayFormatted()
        rule.onNodeWithContentDescription("Select date").performClick()
        rule.onAllNodesWithText(nextDate).onLast().performClick()
    }

    private fun getNextDayFormatted(): String {
        val nextDay = LocalDate.now().plusDays(1)
        val formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy")
        return nextDay.format(formatter)
    }

    fun enterForm(albumName: String) {
        enterAlbumName(albumName)
        enterCover()
        enterReleaseDate()
        enterGenero()
        enterDiscografica()
        enterDescription()

    }

    fun clickOnSave() {
        rule.onNodeWithText("Crear").performScrollTo().performClick()
        rule.onNodeWithText("El álbum ha sido creado con éxito").assertIsDisplayed()
        rule.onNodeWithText("Listo").performClick()
    }

}