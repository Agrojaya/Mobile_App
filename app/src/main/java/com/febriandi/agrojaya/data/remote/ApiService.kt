package com.febriandi.agrojaya.data.remote

import com.febriandi.agrojaya.model.AlamatRequest
import com.febriandi.agrojaya.model.AlamatResponse
import com.febriandi.agrojaya.model.AlamatUpdateRequest
import com.febriandi.agrojaya.model.ArtikelResponse
import com.febriandi.agrojaya.model.BaseResponse
import com.febriandi.agrojaya.model.GoogleUser
import com.febriandi.agrojaya.model.PaketResponse
import com.febriandi.agrojaya.model.PaymentResponse
import com.febriandi.agrojaya.model.PaymentStatus
import com.febriandi.agrojaya.model.TransaksiRequest
import com.febriandi.agrojaya.model.TransaksiResponse
import com.febriandi.agrojaya.model.UpdateUserRequest
import com.febriandi.agrojaya.model.User
import com.febriandi.agrojaya.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("register")
    suspend fun createUser(@Body user: User): Response<User>

    @POST("register")
    suspend fun createGoogleUser(@Body user: GoogleUser): Response<GoogleUser>

    @GET("artikels")
    suspend fun getArtikels(): List<ArtikelResponse>

    @GET("artikel/{id}")
    suspend fun getArtikel(@Path("id") id: Int): ArtikelResponse

    @GET("pakets")
    suspend fun getPakets(): List<PaketResponse>

    @GET("paket/{id}")
    suspend fun getPaket(@Path("id") id: Int): PaketResponse

    @POST("alamat")
    suspend fun simpanAlamat(@Body alamat: AlamatRequest): Response<BaseResponse>

    @GET("alamat/byuid/{uid}")
    suspend fun getAlamatsByUid(@Path("uid") uid: String): List<AlamatResponse>

    @GET("alamat/byid/{id}")
    suspend fun getAlamatById(@Path("id") id: Int): AlamatResponse

    @POST("transaksi")
    suspend fun createTransaksi(@Body transaksi: TransaksiRequest): Response<PaymentResponse>

    @GET("transaksi/byuid/{uid}")
    suspend fun getTransaksisByUid(@Path("uid") uid: String): List<TransaksiResponse>

    @GET("transaksi/byid/{order_id}")
    suspend fun getTransaksiById(@Path("order_id") order_id: String): TransaksiResponse

    @GET("transaksi/status/{order_id}")
    suspend fun getStatusTransaksi(@Path("order_id") order_id: String): PaymentStatus

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: String): UserResponse

    @PUT("users/{userId}")
    suspend fun updateUser(@Path("userId") userId: String, @Body request: UpdateUserRequest): Response<Unit>

    @PUT("alamat/{id}")
    suspend fun updateAlamat(@Path("id") id: Int, @Body alamat: AlamatUpdateRequest): Response<BaseResponse>
}