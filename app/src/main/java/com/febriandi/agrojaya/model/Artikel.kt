package com.febriandi.agrojaya.model

data class Artikel(
    val id: Int,
    val judul: String,
    val penulis: String,
    val isi: String,
    val photo : Int,
    val like: Int,
)
