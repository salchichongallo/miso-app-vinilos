package com.example.vinylsapp.data.remote
import AlbumService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServiceFactory {
    private const val BASE_URL = "http://10.0.2.2:3000/"

    fun makeAlbumService(): AlbumService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AlbumService::class.java)
    }
}