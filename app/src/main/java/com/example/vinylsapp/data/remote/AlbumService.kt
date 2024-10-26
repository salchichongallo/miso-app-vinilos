import com.example.vinylsapp.data.model.Album
import retrofit2.http.GET

interface AlbumService {
    @GET("albums")
    suspend fun getAlbums(): List<Album>
}