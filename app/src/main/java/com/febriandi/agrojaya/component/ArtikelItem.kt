package com.febriandi.agrojaya.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.febriandi.agrojaya.R
import com.febriandi.agrojaya.model.Artikel
import com.febriandi.agrojaya.ui.theme.AgroJayaTheme
import com.febriandi.agrojaya.ui.theme.CustomFontFamily

@Composable
fun ArtikelItem(
    artikel: Artikel,
    modifier: Modifier = Modifier,
    onItemClicked: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .clickable {
                onItemClicked(artikel.id) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.green_50))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Article Image
            Image(
                painter = painterResource(id = artikel.photo),
                contentDescription = "Article thumbnail",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            // Article Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
            ) {
                Text(
                    text = artikel.judul,
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.text_color),
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    fontFamily = CustomFontFamily
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = artikel.isi,
                    fontSize = 10.sp,
                    fontFamily = CustomFontFamily,
                    color = colorResource(id = R.color.natural_400),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        lineHeight = 16.sp,
                        textAlign = Justify
                    )
                )
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                ) {
                    Text(
                        text = artikel.penulis,
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.text_color),
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = CustomFontFamily
                    )
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.icon_like),
                            contentDescription = "like",
                            colorFilter = ColorFilter.tint(colorResource(id = R.color.green_400)),
                            modifier = Modifier
                                .width(50.dp)
                                .padding(start = 25.dp)
                        )


                        Text(
                            text = artikel.like.toString(),
                            fontSize = 12.sp,
                            color = colorResource(id = R.color.text_color),
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = CustomFontFamily
                        )
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArtikelItemPreview() {
    AgroJayaTheme {
        ArtikelItem(
            artikel = Artikel(
                1,
                "Urban Farming",
                "Febriandi",
                "Urban farming, atau pertanian perkotaan, adalah praktik bercocok tanam di dalam atau di sekitar lingkungan perkotaan. Ini melibatkan penanaman Urban farming, atau pertanian perkotaan, adalah praktik bercocok tanam di dalam atau di sekitar lingkungan perkotaan. Ini melibatkan penanaman",
                R.drawable.artikel3,
                189
            ),
            onItemClicked = { artikelId ->
                println("Artikel Id : $artikelId")
            }
        )
    }
}