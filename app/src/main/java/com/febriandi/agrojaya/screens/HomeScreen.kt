package com.febriandi.agrojaya.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.febriandi.agrojaya.R
import com.febriandi.agrojaya.screens.artikel.ArtikelItem
import com.febriandi.agrojaya.component.CarouselCard
import com.febriandi.agrojaya.data.DummyData
import com.febriandi.agrojaya.model.ArtikelResponse
import com.febriandi.agrojaya.ui.theme.CustomFontFamily
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    rootNavController: NavController,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
) {
    val currentUser = FirebaseAuth.getInstance().currentUser?.email?.substringBefore("@") ?: "N/A"
    var search by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Static content (non-scrollable)
        Column {
            // Logo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo3),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .width(170.dp)
                        .padding(top = 20.dp)
                        .align(Alignment.TopEnd)
                )
            }

            // Profile and notification section
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 20.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(60.dp)
                            .border(
                                width = 2.dp,
                                color = colorResource(id = R.color.green_400),
                                shape = CircleShape
                            )
                            .clip(CircleShape)
                    )

                    Text(

                        modifier = Modifier.padding(horizontal = 10.dp),
                        text = "Hi, " + currentUser,
                        fontSize = 14.sp,
                        fontFamily = CustomFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.text_color)
                    )
                }
                Button(
                    onClick = {
                        rootNavController.navigate("notifikasi")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.green_400)
                    ),
                    shape = RoundedCornerShape(30.dp),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(40.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_notifikasi),
                        contentDescription = "Back Icon",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            // Search field
            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                label = null,
                placeholder = {
                    Text(
                        text = "Telusuri",
                        fontFamily = CustomFontFamily
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(30.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = colorResource(id = R.color.green_100),
                    focusedBorderColor = colorResource(id = R.color.green_100),
                    focusedTextColor = colorResource(id = R.color.text_color),
                    focusedLabelColor = colorResource(id = R.color.green_500),
                    unfocusedBorderColor = colorResource(id = R.color.stroke_form),
                    cursorColor = colorResource(id = R.color.text_color)
                ),
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.text_color),
                    fontFamily = CustomFontFamily
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 20.dp)
                    .height(50.dp),
                trailingIcon = {
                    IconButton(onClick = { /* Handle search action */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_search),
                            contentDescription = "Pencarian",
                            tint = colorResource(id = R.color.text_color),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )


        }

        // Scrollable content
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                // Carousel
                CarouselCard()
                // Jadwal Kegiatan Section
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                    text = "Jadwal kegiatan",
                    fontSize = 14.sp,
                    fontFamily = CustomFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.green_500)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.calender),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .alpha(0.8f),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Atur Jadwal Aktivitas \nBertanimu",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = CustomFontFamily,
                            color = colorResource(id = R.color.text_color),
                            modifier = Modifier.padding(top = 10.dp)
                        )

                        Button(
                            onClick = { /* TODO: Handle click */ },
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier
                                .width(150.dp)
                                .height(50.dp)
                                .padding(top = 10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.green_400)
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "Atur Sekarang",
                                fontSize = 12.sp,
                                fontFamily = CustomFontFamily,
                                fontWeight = FontWeight.Medium,
                                color = Color.White,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
                }

                // Artikel Header
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp, horizontal = 20.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 10.dp),
                        text = "Artikel",
                        fontSize = 14.sp,
                        fontFamily = CustomFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.green_500)
                    )

                    Text(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .clickable {
                                rootNavController.navigate("artikel")
                            },
                        text = "Lihat Semua",
                        fontSize = 14.sp,
                        fontFamily = CustomFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.green_500)
                    )
                }
            }


        }
    }
}


