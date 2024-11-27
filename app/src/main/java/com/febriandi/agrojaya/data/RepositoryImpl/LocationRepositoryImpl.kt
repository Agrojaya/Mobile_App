package com.febriandi.agrojaya.data.RepositoryImpl

import com.febriandi.agrojaya.data.Repository.LocationRepository
import com.febriandi.agrojaya.data.remote.LocationApiService
import com.febriandi.agrojaya.model.Kabupaten
import com.febriandi.agrojaya.model.Kecamatan
import com.febriandi.agrojaya.model.Kelurahan
import com.febriandi.agrojaya.model.Provinsi
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val apiService: LocationApiService
) : LocationRepository {
    override suspend fun getProvinsi(): Result<List<Provinsi>> = try {
        Result.success(apiService.getProvinsi())
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getKabupaten(provinsiId: String): Result<List<Kabupaten>> = try {
        Result.success(apiService.getKabupaten(provinsiId))
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getKecamatan(kabupatenId: String): Result<List<Kecamatan>> = try {
        Result.success(apiService.getKecamatan(kabupatenId))
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getKelurahan(kecamatanId: String): Result<List<Kelurahan>> = try {
        Result.success(apiService.getKelurahan(kecamatanId))
    } catch (e: Exception) {
        Result.failure(e)
    }
}