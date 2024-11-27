package com.febriandi.agrojaya.data.RepositoryImpl

import com.febriandi.agrojaya.data.Repository.AlamatRepository
import com.febriandi.agrojaya.data.remote.ApiService
import com.febriandi.agrojaya.model.AlamatRequest
import com.febriandi.agrojaya.model.AlamatResponse
import com.febriandi.agrojaya.model.BaseResponse
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlamatRepositoryImpl @Inject constructor(
    private val alamatApiService: ApiService,
    private val firebaseAuth: FirebaseAuth
) : AlamatRepository {
    override suspend fun simpanAlamat(alamat: AlamatRequest): Result<BaseResponse> = try {
        val response = alamatApiService.simpanAlamat(alamat)
        if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            Result.failure(Exception("Gagal menyimpan alamat"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getAlamatByUid(): List<AlamatResponse> {
        val currentUid = firebaseAuth.currentUser?.uid
            ?: throw IllegalStateException("No user logged in")
        return alamatApiService.getAlamatsByUid(currentUid)
    }

    override suspend fun getAlamatById(id: Int): AlamatResponse =
        alamatApiService.getAlamatById(id)
}