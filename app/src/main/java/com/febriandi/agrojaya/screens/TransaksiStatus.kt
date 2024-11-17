package com.febriandi.agrojaya.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.febriandi.agrojaya.R
import com.febriandi.agrojaya.component.ButtonComponent
import com.febriandi.agrojaya.ui.theme.CustomFontFamily

@Composable
fun TransaksiStatus(
    rootNavController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.transaksi_berhasil),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 60.dp),
                alignment = Alignment.Center
            )

            Text(
                text = "Rp. 500.000",
                fontSize = 30.sp,
                style = TextStyle(
                    lineHeight = 30.sp
                ),
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.text_color),
            )

            Text(
                text = "11 September 2024, 15:06 WIB",
                fontSize = 14.sp,
                style = TextStyle(
                    textAlign = TextAlign.Center
                ),
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.text_color),
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Text(
                text = "Transaksi anda berhasil. Kami akan segera menuju lokasi anda, Terima Kasih.",
                fontSize = 14.sp,
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp
                ),
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.text_color),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )
        }

        ButtonComponent(
            text = "Kembali Ke Beranda"
        ) {
            // Navigasi ke MainScreen dengan menentukan rute awal home
            rootNavController.navigate("mainScreen") {
                // Hapus semua screen dari back stack
                popUpTo(rootNavController.graph.startDestinationId) {
                    inclusive = true
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransaksiStatusPreview() {
    TransaksiStatus(
        rootNavController = rememberNavController()
    )
}