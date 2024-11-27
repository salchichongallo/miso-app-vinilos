package com.example.vinylsapp.artist.mocks

import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.artist.models.Artist
import com.example.vinylsapp.artist.repositories.IArtistRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class ArtistRepositoryMock(artist: List<Artist>) : IArtistRepository {
    private val artists = MutableStateFlow(value = artist)

    override fun getAll(): StateFlow<List<Artist>> {
        return artists
    }

    override suspend fun fetchAll() {}

    override suspend fun getBy(artistId: Int): StateFlow<Artist?> {
        val artistFlow = artists.map { items -> items.firstOrNull { it.id == artistId } }
        return artistFlow.stateIn(scope = CoroutineScope(Dispatchers.Default))
    }

    override suspend fun addToAlbum(artist: Artist, album: Album) {
        artists.update { allArtists ->
            allArtists.map { currentArtist ->
                if (currentArtist.id == artist.id) {
                    currentArtist.copy(albums = currentArtist.albums + album)
                } else {
                    currentArtist
                }
            }
        }
    }
}
