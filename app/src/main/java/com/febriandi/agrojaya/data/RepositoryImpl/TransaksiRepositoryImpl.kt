package com.febriandi.agrojaya.data.RepositoryImpl

import com.febriandi.agrojaya.data.Repository.TransaksiRepository
import com.febriandi.agrojaya.data.remote.ApiService
import com.febriandi.agrojaya.model.PaymentResponse
import com.febriandi.agrojaya.model.TransaksiRequest
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransaksiRepositoryImpl @Inject constructor(
    private val transaksiApiService: ApiService,
    private val firebaseAuth: FirebaseAuth
) : TransaksiRepository {
    override suspend fun simpanTransaksi(transaksi: TransaksiRequest): Result<PaymentResponse> = try {
        val response = transaksiApiService.createTransaksi(transaksi)
        if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            Result.failure(Exception("Gagal melakukan transaksi: ${response.code()} - ${response.message()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}