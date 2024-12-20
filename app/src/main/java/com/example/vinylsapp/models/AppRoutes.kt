package com.example.vinylsapp.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.outlined.Groups
import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.artist.models.Artist
import com.google.gson.Gson

enum class AppRoutes(val value: String) {
    Albums("albums"),
    Artists("artists"),
    Login("login"),
    AlbumDetail("albums/{id}"),
    TrackCreate("/albums/new-track?album={album}"),
    CommentList("/albums/{id}/comments"),
    AlbumCreate("albums/new"),
    ArtistDetail("/artists/{artistId}"),
    ArtistAlbum("/artists/{artistId}/albums?album={album}")
}

val topLevelRoutes = listOf(
    TopLevelRoute("Álbumes", AppRoutes.Albums, Icons.Filled.Folder),
    TopLevelRoute("Artistas", AppRoutes.Artists, Icons.Outlined.Groups),
    TopLevelRoute("Login", AppRoutes.Login, Icons.AutoMirrored.Filled.DirectionsWalk),
)

fun buildAlbumDetailRoute(id: Int) = AppRoutes.AlbumDetail.value.replace(
    oldValue = "{id}",
    newValue = id.toString()
)

fun buildTrackNewScreenRoute(album: Album): String {
    val gson = Gson()
    val serializedAlbum = gson.toJson(album)
    return AppRoutes.TrackCreate.value.replace("{album}", serializedAlbum)
}

fun buildCommentListScreenRoute(albumId: Int) = AppRoutes.CommentList.value.replace(
    oldValue = "{id}",
    newValue = albumId.toString()
)

fun buildArtistDetailRoute(artist: Artist): String {
    return AppRoutes.ArtistDetail.value.replace("{artistId}", artist.id.toString())
}

fun buildArtistAlbumRoute(artistId: Int): String {
    return AppRoutes.ArtistAlbum.value.replace("{artistId}", artistId.toString())
}
