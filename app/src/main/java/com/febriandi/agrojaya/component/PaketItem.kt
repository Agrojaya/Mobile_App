package com.febriandi.agrojaya.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.febriandi.agrojaya.R
import com.febriandi.agrojaya.model.Paket
import com.febriandi.agrojaya.ui.theme.AgroJayaTheme
import com.febriandi.agrojaya.ui.theme.CustomFontFamily


@Composable
fun PaketItem(
    paket: Paket,
    modifier: Modifier = Modifier,
    onItemClicked: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .clickable { onItemClicked(paket.id) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Background Image
            Image(
                painter = painterResource(id = paket.photo),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            // Semi-transparent overlay for better text readability
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                    .background(
                        color = colorResource(id = R.color.natural_900).copy(alpha = 0.3f),
                        shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
                    )

                    .border(
                        width = 0.5.dp,
                        color = Color.White.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
                    )
            ) {

            }

            // Content Column
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .align(Alignment.BottomEnd),
                verticalArrangement = Arrangement.Bottom
            ) {


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = paket.nama_paket,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = CustomFontFamily,
                            color = Color.White,
                            style = TextStyle(
                                lineHeight = 22.sp,
                            ),
                        )
                        Text(
                            text = paket.harga,
                            fontSize = 14.sp,
                            fontFamily = CustomFontFamily,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            style = TextStyle(
                                lineHeight = 22.sp,
                                textAlign = TextAlign.Justify
                            ),
                        )

                    }

                    Button(
                        onClick = { onItemClicked(paket.id) },
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
                            verticalAlignment = Alignment.CenterVertically, // Mengatur agar teks dan gambar sejajar vertikal
                            horizontalArrangement = Arrangement.Center, // Agar teks dan gambar terletak di tengah horizontal
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
private  fun PaketItemPreview(){
    AgroJayaTheme {
        PaketItem(
            paket = Paket(
                1,
                "Paket Menengah",
                "Rp 1.000.000",
                "Tes",
                "ksksk",
                R.drawable.paket3
            ),
            onItemClicked = { paketId ->
                println("Paket Id : $paketId")
            }
        )
    }
}