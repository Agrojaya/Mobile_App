package com.febriandi.agrojaya.model

import java.util.UUID


data class Notifikasi(
    val id: Int,
    val judul: String,
    val pesan: String,
    val tanggal: String,
)

data class NotificationModel(
    val id: String = UUID.randomUUID().toString(),
    val title: String? = null,
    val body: String? = null,
    val waktu: Long = System.currentTimeMillis()
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NotificationModel

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}