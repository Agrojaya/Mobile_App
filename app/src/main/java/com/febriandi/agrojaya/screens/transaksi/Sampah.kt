//package com.febriandi.agrojaya.screens
//
//import android.widget.Toast
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.items
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Checkbox
//import androidx.compose.material3.CheckboxDefaults
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
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
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import coil.compose.AsyncImage
//import coil.request.ImageRequest
//import com.febriandi.agrojaya.R
//import com.febriandi.agrojaya.component.ButtonBack
//import com.febriandi.agrojaya.model.AlamatResponse
//import com.febriandi.agrojaya.model.PaketResponse
//import com.febriandi.agrojaya.screens.Paket.DetailPaketViewModel
//import com.febriandi.agrojaya.screens.alamat.AlamatViewModel
//import com.febriandi.agrojaya.screens.transaksi.component.AlamatCard
//import com.febriandi.agrojaya.screens.transaksi.viewmodel.TransaksiViewModel
//import com.febriandi.agrojaya.ui.theme.CustomFontFamily
//import com.febriandi.agrojaya.utils.Resource
//
//@Composable
//fun PembelianScreen(
//    navController: NavController,
//    modifier: Modifier = Modifier,
//    onBackClick: () -> Unit,
//    paketId: Int?,
//    transaksiViewModel: TransaksiViewModel = hiltViewModel(),
//    viewModel: DetailPaketViewModel = hiltViewModel(),
//    alamatViewModel: AlamatViewModel = hiltViewModel()
//) {
//    val paketState = viewModel.paketState.collectAsState()
//    val alamatState by alamatViewModel.alamatState.collectAsState()
//
//    val alamatAddedFlag = navController.currentBackStackEntry
//        ?.savedStateHandle
//        ?.get<Boolean>("alamat_added") ?: false
//
//    LaunchedEffect(paketId) {
//        paketId?.let { viewModel.loadPaket(it) }
//        alamatViewModel.loadAlamat()
//    }
//
//    LaunchedEffect(alamatAddedFlag) {
//        if (alamatAddedFlag) {
//            alamatViewModel.loadAlamat()
//            // Reset flag
//            navController.currentBackStackEntry
//                ?.savedStateHandle
//                ?.set("alamat_added", false)
//        }
//    }
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        when (val state = paketState.value) {
//            is Resource.Loading -> {
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    CircularProgressIndicator(
//                        color = colorResource(id = R.color.green_500)
//                    )
//                }
//            }
//            is Resource.Success -> {
//                PembelianContent(
//                    paket = state.data,
//                    alamatState = alamatState,
//                    onBackClick = {
//                        navController.navigateUp()
//                    },
//                    navController = navController
//                )
//            }
//            is Resource.Error -> {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(16.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    Text(
//                        text = state.message ?: "Terjadi kesalahan",
//                        color = Color.Red,
//                        fontFamily = CustomFontFamily
//                    )
//                    Button(
//                        onClick = { paketId?.let { viewModel.loadPaket(it) } },
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = colorResource(id = R.color.green_500)
//                        )
//                    ) {
//                        Text(
//                            text = "Coba Lagi",
//                            fontFamily = CustomFontFamily,
//                            color = Color.White
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//@Composable
//private fun PembelianContent(
//    paket: PaketResponse,
//    alamatState: Resource<List<AlamatResponse>>,
//    onBackClick: () -> Unit,
//    modifier: Modifier = Modifier,
//    navController: NavController
//) {
//    val scrollState = rememberScrollState()
//    val categories = paket.variasi_bibit
//        .replace("\"", "")
//        .split(",")
//        .map { it.trim() }
//
//
//    val selectedCategories = remember { mutableStateListOf<String>() }
//    var selectedAlamat by remember { mutableStateOf<AlamatResponse?>(null) }
//
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
//                    .padding(top = 20.dp, start = 20.dp, end = 20.dp)
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
//                    .padding(vertical = 10.dp, horizontal = 20.dp)
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.loc),
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
//            when (alamatState) {
//                is Resource.Loading -> {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(100.dp),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        CircularProgressIndicator(
//                            color = colorResource(id = R.color.green_500)
//                        )
//                    }
//                }
//                is Resource.Success -> {
//                    val alamats = alamatState.data
//                    if (alamats.isNotEmpty()) {
//                        LazyRow(
//                            modifier = Modifier
//                                .fillMaxWidth(),
//                            horizontalArrangement = Arrangement.spacedBy(8.dp),
//                            contentPadding = PaddingValues(horizontal = 16.dp)
//                        ) {
//                            items(
//                                items = alamats,
//                                key = { it.id }
//                            ) { alamat ->
//                                AlamatCard(
//                                    alamat = alamat,
//                                    isSelected = selectedAlamat?.id == alamat.id,
//                                    onSelect = { selectedAlamat = it }
//                                )
//                            }
//                        }
//                    } else {
//                        Box(
//                            modifier = Modifier.fillMaxWidth(),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            Text(
//                                text = "Tidak ada Alamat tersedia",
//                                fontFamily = CustomFontFamily
//                            )
//                        }
//                    }
//                }
//                is Resource.Error -> {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(16.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Text(
//                            text = alamatState.message ?: "Terjadi kesalahan",
//                            color = Color.Red,
//                            fontFamily = CustomFontFamily
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
//                    text = "Harga : Rp ${String.format("%,d", paket.harga).replace(',', '.')} \nTotal harga sudah termasuk biaya keseluruhan pemasangan.",
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
//                            Text(
//                                text = "Rp ${String.format("%,d", paket.harga).replace(',', '.')}",
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
//                                if (selectedAlamat != null && selectedCategories.isNotEmpty()) {
//                                    transaksiViewModel.createTransaction(
//                                        paketId = paket.id,
//                                        namaPaket = paket.nama_paket,
//                                        alamatId = selectedAlamat!!.id,
//                                        totalHarga = paket.harga,
//                                        variasiBibit = selectedCategories.joinToString(",")
//                                    )
//                                }
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
//                        val paymentState = transaksiViewModel.paymentState.collectAsState()
//
//                        when (val state = paymentState.value) {
//                            is Resource.Success -> {
//
//                                LaunchedEffect(state) {
//                                    navController.navigate(
//                                        "payment-webview/${state.data.token.redirect_url}"
//                                    )
//                                }
//                            }
//
//                            is Resource.Error -> {
//                                // Tampilkan pesan error
//                                Toast.makeText(
//                                    LocalContext.current,
//                                    state.message ?: "Terjadi kesalahan",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            }
//
//                            is Resource.Loading -> {
//                                // Tampilkan loading indicator
//                                CircularProgressIndicator()
//                            }
//                        }
//
//                    }
//
//                }
//            }
//        }
//
//    }
//
//}
//}
//
