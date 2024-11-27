package com.febriandi.agrojaya.model

data class TransaksiRequest(
    val uid: String,
    val nama_pengguna: String,
    val email: String,
    val paket_id: Int,
    val nama_paket: String,
    val alamat_id: Int,
    val total_harga: Int,
    val variasi_bibit: String
)

data class PaymentResponse(
    val success: Boolean,
    val msg: String,
    val token: TokenData
)

data class TokenData (
    val token: String,
    val redirect_url: String
)