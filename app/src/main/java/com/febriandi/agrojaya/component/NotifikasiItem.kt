package com.febriandi.agrojaya.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.febriandi.agrojaya.R
import com.febriandi.agrojaya.model.ArtikelResponse
import com.febriandi.agrojaya.model.Notifikasi
import com.febriandi.agrojaya.ui.theme.AgroJayaTheme
import com.febriandi.agrojaya.ui.theme.CustomFontFamily

@Composable
fun NotifikasiItem(
    notifikasi: Notifikasi,
    modifier: Modifier = Modifier
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(horizontal = 20.dp, vertical = 3.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.green_50))
        ) {
        Column (
            modifier = Modifier
                .padding(15.dp)
        ) {
            Text(
                text = notifikasi.judul,
                fontSize = 12.sp,
                color = colorResource(id = R.color.text_color),
                fontWeight = FontWeight.Bold,
                fontFamily = CustomFontFamily
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = notifikasi.pesan,
                fontSize = 12.sp,
                color = colorResource(id = R.color.natural_500),
                fontWeight = FontWeight.Normal,
                fontFamily = CustomFontFamily
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = notifikasi.tanggal,
                fontSize = 12.sp,
                color = colorResource(id = R.color.text_color),
                fontWeight = FontWeight.Normal,
                fontFamily = CustomFontFamily
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NotifikasiItemPreview() {
    AgroJayaTheme {
        NotifikasiItem(
            notifikasi = Notifikasi(
                1,
                "Rekomendasi Artikel Untuk Tanamanmu!",
                "Artikel yang cocok untuk memantau proses pertanianmu. Yuk, baca sekaarang!",
                "28-10-2024 11:40",
            )
        )
    }
}