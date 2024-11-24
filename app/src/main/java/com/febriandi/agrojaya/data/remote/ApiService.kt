package com.febriandi.agrojaya.data.remote

import com.febriandi.agrojaya.model.ArtikelResponse
import com.febriandi.agrojaya.model.GoogleUser
import com.febriandi.agrojaya.model.PaketResponse
import com.febriandi.agrojaya.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("register")
    suspend fun createUser(@Body user: User): Response<User>

    @POST("register")
    suspend fun createGoogleUser(@Body user: GoogleUser): Response<GoogleUser>

    @GET("data_artikel")
    suspend fun getArtikels(): List<ArtikelResponse>

    @GET("data_artikel/{id}")
    suspend fun getArtikel(@Path("id") id: Int): ArtikelResponse

    @GET("data_paket")
    suspend fun getPakets(): List<PaketResponse>

    @GET("data_paket/{id}")
    suspend fun getPaket(@Path("id") id: Int): PaketResponse
}