package com.febriandi.agrojaya.data.Repository

import com.febriandi.agrojaya.model.AlamatRequest
import com.febriandi.agrojaya.model.AlamatResponse
import com.febriandi.agrojaya.model.BaseResponse

interface AlamatRepository {
    suspend fun simpanAlamat(alamat: AlamatRequest): Result<BaseResponse>
    suspend fun getAlamatByUid(): List<AlamatResponse>
    suspend fun getAlamatById(id: Int): AlamatResponse
}