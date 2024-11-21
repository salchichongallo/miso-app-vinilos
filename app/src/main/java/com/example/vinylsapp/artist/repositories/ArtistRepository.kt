package com.example.vinylsapp.artist.repositories

import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.artist.models.Artist
import com.example.vinylsapp.artist.repositories.services.NetworkArtistServiceAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ArtistRepository(private val serviceAdapter: NetworkArtistServiceAdapter) :
    IArtistRepository {

    private val artists = MutableStateFlow(value = listOf<Artist>())

    override fun getAll(): StateFlow<List<Artist>> {
        return artists
    }

    override suspend fun fetchAll() {
        artists.emit(value = serviceAdapter.fetchArtists())
    }

    override suspend fun getBy(artistId: Int): StateFlow<Artist?> {
        val artistFlow = artists.map { items -> items.firstOrNull { it.id == artistId } }
        return artistFlow.stateIn(scope = CoroutineScope(Dispatchers.Default))
    }

    override suspend fun addToAlbum(artist: Artist, album: Album) {
        serviceAdapter.addToAlbum(artist.id, album.id)
    }
}
