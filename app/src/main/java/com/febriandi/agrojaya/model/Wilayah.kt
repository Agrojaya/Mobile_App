package com.febriandi.agrojaya.model

data class Provinsi(
    val id: String,
    val nama: String
) {
    override fun toString(): String = nama
}


data class Kabupaten(
    val id: String,
    val provinsi_id: String,
    val nama: String
){
    override fun toString(): String = nama
}


data class Kecamatan(
    val id: String,
    val kabupaten_id: String,
    val nama: String
){
    override fun toString(): String = nama
}


data class Kelurahan(
    val id: String,
    val kecamatan_id: String,
    val nama: String
){
    override fun toString(): String = nama
}
