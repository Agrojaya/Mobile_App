package com.febriandi.agrojaya.model

data class AlamatRequest(
    val uid: String,
    val nama: String,
    val noHp: String,
    val provinsi: String,
    val kabupaten: String,
    val kecamatan: String,
    val kelurahan: String,
    val alamatLengkap: String,
    val catatan: String
)

data class BaseResponse(
    val success: Boolean,
    val message: String,
    val data: Any? = null
)

data class AlamatResponse(
    val id: Int,
    val uid: String,
    val nama: String,
    val noHp: String,
    val provinsi: String,
    val kabupaten: String,
    val kecamatan: String,
    val kelurahan: String,
    val alamatLengkap: String,
    val catatan: String
)

data class AlamatUpdateRequest(
    val id: Int? = null,
    val uid: String,
    val nama: String,
    val noHp: String,
    val provinsi: String,
    val kabupaten: String,
    val kecamatan: String,
    val kelurahan: String,
    val alamatLengkap: String,
    val catatan: String
)