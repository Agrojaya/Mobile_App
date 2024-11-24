//package com.febriandi.agrojaya.screens
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.IntrinsicSize
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.items
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Checkbox
//import androidx.compose.material3.CheckboxDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.ColorFilter
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.core.app.NotificationCompat.Style
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import coil.compose.AsyncImage
//import coil.request.ImageRequest
//import com.febriandi.agrojaya.R
//import com.febriandi.agrojaya.component.ButtonBack
//import com.febriandi.agrojaya.data.DummyData
//import com.febriandi.agrojaya.model.Paket
//import com.febriandi.agrojaya.ui.theme.CustomFontFamily
//
//@Composable
//fun PembelianScreen(
//    navController: NavController,
//    modifier: Modifier = Modifier,
//    onBackClick: () -> Unit,
//    paketId: Int?
//) {
//    val newPaketList = DummyData.paket.filter { paket ->
//        paket.id == paketId
//    }
//
//    if (newPaketList.isNotEmpty()) {
//        PembelianContent(
//            paket = newPaketList[0],
//            navController = navController,  // Pass navController to content
//            onBackClick = {
//                navController.navigateUp()
//            }
//        )
//    }
//}
//@Composable
//private fun PembelianContent(
//    paket: Paket,
//    onBackClick: () -> Unit,
//    modifier: Modifier = Modifier,
//    navController: NavController
//) {
//    val scrollState = rememberScrollState()
//    val categories = listOf(
//        "Cabai", "Bayam", "Kangkung", "Selada", "Tomat",
//        "Pak Choi", "Kailan", "Sawi", "Basil", "Mint"
//    )
//
//
//    val selectedCategories = remember { mutableStateListOf<String>() }
//
//    Box(
//        modifier = Modifier.fillMaxSize()
//    ){
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.White)
//        ){
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 20.dp, horizontal = 20.dp)
//            ) {
//                ButtonBack(onClick = onBackClick)
//                Text(
//                    modifier = Modifier.padding(horizontal = 10.dp),
//                    text = "Detail Pesanan",
//                    fontSize = 16.sp,
//                    fontFamily = CustomFontFamily,
//                    fontWeight = FontWeight.SemiBold,
//                    color = colorResource(id = R.color.text_color)
//                )
//            }
//
//            Row (
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 20.dp, horizontal = 20.dp)
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.icon_lokasi),
//                    contentDescription = "Icon",
//                    modifier = Modifier.size(25.dp),
//                    colorFilter = ColorFilter.tint(colorResource(id = R.color.text_color))
//                )
//                Text(
//                    modifier = Modifier.padding(horizontal = 10.dp),
//                    text = "Alamat Pengiriman",
//                    fontSize = 14.sp,
//                    fontFamily = CustomFontFamily,
//                    fontWeight = FontWeight.SemiBold,
//                    color = colorResource(id = R.color.text_color)
//                )
//            }
//            Row (
//                modifier = Modifier.padding(horizontal = 20.dp)
//            ) {
//                Card(modifier = Modifier
//                    .width(260.dp)
//                    .height(120.dp),
//                    shape = RoundedCornerShape(10.dp),
//                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//                    colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.green_50))
//                ){
//                    Row (
//                        modifier= Modifier
//                            .padding(10.dp)
//                            .fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ){
//                        Text(
//                            text = "Alamat :",
//                            fontSize = 12.sp,
//                            fontFamily = CustomFontFamily,
//                            fontWeight = FontWeight.SemiBold,
//                            color = colorResource(id = R.color.text_color)
//                        )
//                        Image(
//                            painter = painterResource(id = R.drawable.icon_edit),
//                            contentDescription = "Icon",
//                            modifier = Modifier
//                                .size(20.dp)
//                                .clickable {
//
//                                },
//                            colorFilter = ColorFilter.tint(colorResource(id = R.color.text_color)),
//                        )
//                    }
//                    Text(
//                        modifier = Modifier.padding(horizontal = 10.dp),
//                        text = "Jalan Pemuda no. 19 rumah warna kuning",
//                        fontSize = 12.sp,
//                        maxLines = 2,
//                        style = TextStyle(
//                            lineHeight = 17.sp
//                        ),
//                        overflow = TextOverflow.Ellipsis,
//                        fontFamily = CustomFontFamily,
//                        fontWeight = FontWeight.Medium,
//                        color = colorResource(id = R.color.text_color)
//                    )
//                    Text(
//                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
//                        text =  "Kontak :  +6241234569333",
//                        fontSize = 12.sp,
//                        maxLines = 2,
//                        style = TextStyle(
//                            lineHeight = 25.sp
//                        ),
//                        overflow = TextOverflow.Ellipsis,
//                        fontFamily = CustomFontFamily,
//                        fontWeight = FontWeight.Medium,
//                        color = colorResource(id = R.color.text_color)
//                    )
//                }
//                Spacer(modifier = Modifier.width(20.dp))
//                Card(modifier = Modifier
//                    .fillMaxWidth()
//                    .height(120.dp),
//                    shape = RoundedCornerShape(10.dp),
//                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//                    colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.green_50))
//                ){
//                    Column (
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        modifier = Modifier.fillMaxSize()
//                    ) {
//                        Image(
//                            painter = painterResource(id = R.drawable.icon_tambah),
//                            contentDescription = "Icon",
//                            modifier = Modifier
//                                .size(30.dp)
//                                .clickable {
//
//                                },
//                            colorFilter = ColorFilter.tint(colorResource(id = R.color.text_color)),
//                        )
//                    }
//                }
//            }
//            Text(
//                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
//                text = paket.nama_paket,
//                fontSize = 16.sp,
//                fontFamily = CustomFontFamily,
//                fontWeight = FontWeight.SemiBold,
//                color = colorResource(id = R.color.text_color)
//            )
//            Row (
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp)
//            ){
//                AsyncImage(
//                    model = ImageRequest.Builder(LocalContext.current)
//                        .data(data = paket.photo)
//                        .build(),
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .width(120.dp)
//                        .height(120.dp)
//                        .clip(RoundedCornerShape(10.dp)),
//                    contentDescription = "Foto Paket"
//                )
//                Text(
//                    text = "Harga : " + paket.harga +"\nTotal harga sudah termasuk biaya keseluruhan pemasangan.",
//                    modifier = Modifier.padding(start = 20.dp),
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.Medium,
//                    fontFamily = CustomFontFamily,
//                    color = colorResource(id = R.color.text_color),
//                    style = TextStyle(lineHeight = 25.sp)
//                )
//            }
//            Text(
//                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
//                text = "Pilih Varian Bibit",
//                fontSize = 14.sp,
//                fontFamily = CustomFontFamily,
//                fontWeight = FontWeight.SemiBold,
//                color = colorResource(id = R.color.text_color)
//            )
//            LazyVerticalGrid(
//                columns = GridCells.Fixed(2),
//                modifier = Modifier
//                    .padding(horizontal = 16.dp),
//                contentPadding = PaddingValues(0.dp),
//                horizontalArrangement = Arrangement.spacedBy(8.dp),
//                verticalArrangement = Arrangement.spacedBy(0.dp)
//            ) {
//                items(categories) { category ->
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                    ) {
//                        Checkbox(
//                            checked = selectedCategories.contains(category),
//                            onCheckedChange = { isChecked ->
//                                if (isChecked) {
//                                    selectedCategories.add(category)
//                                } else {
//                                    selectedCategories.remove(category)
//                                }
//                            },
//                            colors = CheckboxDefaults.colors(
//                                checkedColor = colorResource(id = R.color.green_400),
//                                uncheckedColor = Color.Gray
//                            )
//                        )
//
//                        Text(
//                            text = category,
//                            fontSize = 16.sp,
//                            fontFamily = CustomFontFamily,
//                            color = colorResource(id = R.color.text_color),
//                            modifier = Modifier.padding(start = 8.dp)
//                        )
//                    }
//                }
//            }
//            Box(modifier = Modifier.fillMaxSize()){
//                Column(
//                    horizontalAlignment = Alignment.End,
//                    verticalArrangement = Arrangement.Center,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(100.dp)
//                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
//                        .background(
//                            color = colorResource(id = R.color.green_50)
//                        )
//                        .align(Alignment.BottomCenter)
//                ) {
//                    Row (
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically,
//                        modifier = Modifier.fillMaxSize()
//                            .padding(horizontal = 20.dp)
//                    ) {
//                        Column {
//                            Text(
//                                text = "Total",
//                                fontSize = 16.sp,
//                                fontFamily = CustomFontFamily,
//                                fontWeight = FontWeight.SemiBold,
//                                style = TextStyle(
//                                    lineHeight = 20.sp
//                                ),
//                                color = colorResource(id = R.color.green_400)
//                            )
//
//                            Text(
//                                text = paket.harga,
//                                fontSize = 18.sp,
//                                style = TextStyle(
//                                    lineHeight = 20.sp
//                                ),
//                                fontFamily = CustomFontFamily,
//                                fontWeight = FontWeight.Bold,
//                                color = colorResource(id = R.color.text_color)
//                            )
//                        }
//                        Button(
//                            onClick = {
//                                navController.navigate("transaksi")
//                            },
//                            contentPadding = PaddingValues(0.dp),
//                            modifier = Modifier
//                                .width(150.dp)
//                                .height(50.dp)
//                                .padding(top = 10.dp),
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = colorResource(id = R.color.green_400)
//                            ),
//                            shape = RoundedCornerShape(8.dp)
//                        ) {
//                            Text(
//                                text = "Bayar",
//                                fontSize = 14.sp,
//                                fontFamily = CustomFontFamily,
//                                fontWeight = FontWeight.Medium,
//                                color = Color.White,
//                                modifier = Modifier.padding(vertical = 8.dp)
//                            )
//                        }
//                    }
//                }
//            }
//
//        }
//
//    }
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun PembelianScreenPreview(){
//    PembelianContent(
//        paket = DummyData.paket[0],
//        navController = rememberNavController(),
//        onBackClick = {}
//    )
//}
