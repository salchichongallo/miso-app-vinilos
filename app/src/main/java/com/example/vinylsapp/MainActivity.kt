package com.example.vinylsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.vinylsapp.album.repositories.AlbumRepository
import com.example.vinylsapp.album.repositories.services.RetrofitServiceFactory
import com.example.vinylsapp.album.tracks.repositories.TrackRepository
import com.example.vinylsapp.album.tracks.repositories.services.TrackRetrofitInstance
import com.example.vinylsapp.artist.repositories.ArtistRepository
import com.example.vinylsapp.artist.repositories.services.ArtistRetrofitInstance
import com.example.vinylsapp.comment.repositories.CommentRepository
import com.example.vinylsapp.comment.repositories.services.CommentRetrofitInstance
import com.example.vinylsapp.database.VinylsRoomDatabase
import com.example.vinylsapp.ui.elements.RootNavigation


class MainActivity : ComponentActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        val database = VinylsRoomDatabase.getDatabase(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val albumRepo = AlbumRepository(serviceAdapter = RetrofitServiceFactory.makeAlbumService(), albumDao = database.albumDao())
        val trackRepository =
            TrackRepository(serviceAdapter = TrackRetrofitInstance.makeTrackService())
        val commentRepo =
            CommentRepository(serviceAdapter = CommentRetrofitInstance.makeCommentService())
        val artistRepo = ArtistRepository(serviceAdapter = ArtistRetrofitInstance.makeArtistService())

        setContent {
            RootNavigation(albumRepo, trackRepository, commentRepository = commentRepo, artistRepository = artistRepo)
        }
    }
}
