package com.example.vinylsapp.artist.repositories

import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.artist.models.Artist
import com.example.vinylsapp.artist.repositories.services.NetworkArtistServiceAdapter
import com.example.vinylsapp.database.dao.ArtistsDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class ArtistRepository(private val serviceAdapter: NetworkArtistServiceAdapter, private val artistsDao: ArtistsDao) :
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

    override suspend fun refreshData(): List<Artist> {
        try {
            val cached = artistsDao.getAll()
            return cached.ifEmpty {
                getAll()
            }
        } catch (e: Exception) {
            return getAll()
        }
    }


}
