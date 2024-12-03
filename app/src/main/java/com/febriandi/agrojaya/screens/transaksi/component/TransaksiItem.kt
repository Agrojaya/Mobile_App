package com.febriandi.agrojaya.screens.transaksi.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Justify
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.febriandi.agrojaya.R
import com.febriandi.agrojaya.model.AlamatResponse
import com.febriandi.agrojaya.model.ArtikelResponse
import com.febriandi.agrojaya.model.TransaksiResponse
import com.febriandi.agrojaya.screens.alamat.AlamatItem
import com.febriandi.agrojaya.ui.theme.AgroJayaTheme
import com.febriandi.agrojaya.ui.theme.CustomFontFamily

@Composable
fun TransaksiItem(
    transaksi: TransaksiResponse,
    modifier: Modifier = Modifier,
    onItemClicked: (String) -> Unit
) {
    var isLoading by remember { mutableStateOf(true) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(horizontal = 20.dp, vertical = 10.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.white))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                AsyncImage(
                    model = transaksi.photo_paket,
                    contentDescription = "Paket thumbnail",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    onLoading = { isLoading = true },
                    onSuccess = { isLoading = false }
                )

                if (isLoading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = colorResource(id = R.color.green_400)
                        )
                    }
                }
            }
            // Article Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
            ) {
                Text(
                    text = transaksi.nama_paket,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.text_color),
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    fontFamily = CustomFontFamily
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Status Pembayaran",
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.text_color),
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = CustomFontFamily,
                    style = TextStyle(
                        lineHeight = 10.sp
                    )
                )
                Text(
                    text = transaksi.status_pembayaran,
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.text_color),
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = CustomFontFamily,
                    style = TextStyle(
                        lineHeight = 10.sp
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                ) {
                    Text(
                        text = "Rp ${String.format("%,d", transaksi.total_harga).replace(',', '.')}",
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.text_color),
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = CustomFontFamily
                    )
                    Button(
                        onClick = { onItemClicked(transaksi.order_id) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.green_400)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .width(90.dp)
                            .height(40.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "Detail",
                                fontSize = 14.sp,
                                fontFamily = CustomFontFamily,
                                fontWeight = FontWeight.Medium,
                                color = Color.White,
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Image(
                                painter = painterResource(id = R.drawable.icon_detail),
                                contentDescription = "Icon",
                                modifier = Modifier.size(20.dp),
                                colorFilter = ColorFilter.tint(colorResource(id = R.color.white))
                            )
                        }
                    }

                }

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun TransaksiItemPreview() {
    val sampleTransaksi = TransaksiResponse(
        id = 1,
        order_id = "AGROJAYA88f0440b3-2a64-4c9f-8040-4d921323aa0d",
        uid = "wjwjwjwjjw",
        paket_id = 1,
        alamat_id = 1,
        total_harga = 500000,
        variasi_bibit = "Sawi",
        tanggal = "20/02/2024",
        status_pembayaran = "Pending",
        status_transaksi = "Sedang diproses",
        snap_token = "aaaa",
        snap_redirect_url = "aaaa",
        nama_paket = "Paket Dasar",
        photo_paket = "R.drawable.paket1",
        nama_alamat = "Febriandi",
        noHp = "082991829282",
        alamatLengkap = "Griya Exotica Cinangka Blok M no.3",
        kelurahan = "Cinangka",
        kecamatan = "Sawangan",
        kabupaten = "Kota Depok",
        provinsi = "Jawa Barat",
        catatan = "Lurus sampai ujung gang, baru belok kanan"
    )

    MaterialTheme {
        TransaksiItem(
            transaksi = sampleTransaksi,
            onItemClicked = {}

        )
    }
}


