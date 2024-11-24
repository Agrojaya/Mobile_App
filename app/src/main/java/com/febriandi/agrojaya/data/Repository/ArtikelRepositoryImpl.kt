package com.febriandi.agrojaya.data.Repository

import com.febriandi.agrojaya.data.remote.ApiService
import com.febriandi.agrojaya.model.ArtikelResponse
import com.febriandi.agrojaya.utils.Resource
import javax.inject.Inject

class ArtikelRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): ArtikelRepository {

    override suspend fun getArtikels(): Resource<List<ArtikelResponse>> {
        return try {
            val response = apiService.getArtikels()
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }

    override suspend fun getArtikelById(id: Int): Resource<ArtikelResponse> {
        return try {
            val response = apiService.getArtikel(id)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}