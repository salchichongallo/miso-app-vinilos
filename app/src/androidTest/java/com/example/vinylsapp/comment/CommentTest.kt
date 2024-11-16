package com.example.vinylsapp.comment

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinylsapp.album.mocks.AlbumRepositoryMock
import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.models.AlbumGenre
import com.example.vinylsapp.album.pom.AlbumDetailPom
import com.example.vinylsapp.album.pom.AlbumScreenPom
import com.example.vinylsapp.album.tracks.mock.TrackRepositoryMock
import com.example.vinylsapp.comment.mocks.CommentRepositoryMock
import com.example.vinylsapp.comment.pom.CommentCreateFormPom
import com.example.vinylsapp.comment.pom.CommentListScreenPom
import com.example.vinylsapp.comment.pom.CommentNewSuccessAlertPom
import com.example.vinylsapp.login.pom.LoginPom
import com.example.vinylsapp.ui.elements.RootNavigation
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CommentTest {
    private lateinit var login: LoginPom
    private lateinit var albumList: AlbumScreenPom
    private lateinit var commentList: CommentListScreenPom
    private lateinit var commentCreateForm: CommentCreateFormPom
    private lateinit var commentNewSuccessAlert: CommentNewSuccessAlertPom

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        login = LoginPom(composeTestRule)
        albumList = AlbumScreenPom(composeTestRule)
        commentList = CommentListScreenPom(composeTestRule)
        commentCreateForm = CommentCreateFormPom(composeTestRule)
        commentNewSuccessAlert = CommentNewSuccessAlertPom(composeTestRule)
    }

    @After
    fun sleep() {
        Thread.sleep(2000)
    }

    @Test
    fun shouldCreateCommentSuccessfully() {
        val commentDescription = "Excelente álbum${System.currentTimeMillis()}"
        val album = Album(
            id = 100,
            name = "3 Buscando América",
            cover = "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
            genre = AlbumGenre.SALSA,
            releaseDate = "1984-08-01T00:00:00.000Z"
        )
        composeTestRule.setContent {
            RootNavigation(
                albumRepo = AlbumRepositoryMock(listOf(album)),
                trackRepository = TrackRepositoryMock(mutableListOf()),
                commentRepository = CommentRepositoryMock()
            )
        }

        val albumDetail = goToAlbumDetail()
        goToAlbumComments(albumDetail)
        openCommentCreateModal()
        fillAndSubmitCommentForm(commentDescription)

        composeTestRule.onNodeWithText(commentDescription).assertExists()
    }

    private fun goToAlbumDetail(): AlbumDetailPom {
        login.loginAsCollector()
        val albumItem = albumList.firstAlbum()
        return albumItem.click()
    }

    private fun goToAlbumComments(albumDetailPom: AlbumDetailPom) {
        val albumCommentsButton = albumDetailPom.commentsButton()
        albumCommentsButton.performClick()
    }

    private fun openCommentCreateModal() {
        val createCommentButton = commentList.createCommentButton()
        createCommentButton.performClick()
    }

    private fun fillAndSubmitCommentForm(description: String) {
        commentCreateForm.enterDescription(description)
        commentCreateForm.enterRating(5)
        commentCreateForm.commentButton().performClick()
        commentNewSuccessAlert.dismissButton().performClick() // Close success alert
    }
}