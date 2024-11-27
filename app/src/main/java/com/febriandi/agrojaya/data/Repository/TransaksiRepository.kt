package com.febriandi.agrojaya.data.Repository

import com.febriandi.agrojaya.model.PaymentResponse
import com.febriandi.agrojaya.model.TransaksiRequest

interface TransaksiRepository {
    suspend fun simpanTransaksi(transaksi: TransaksiRequest): Result<PaymentResponse>
}