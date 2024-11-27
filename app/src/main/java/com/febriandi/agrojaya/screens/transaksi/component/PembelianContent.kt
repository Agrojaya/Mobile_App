package com.febriandi.agrojaya.screens.transaksi.component

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.febriandi.agrojaya.R
import com.febriandi.agrojaya.component.ButtonBack
import com.febriandi.agrojaya.model.AlamatResponse
import com.febriandi.agrojaya.model.PaketResponse
import com.febriandi.agrojaya.screens.transaksi.TransaksiViewModel
import com.febriandi.agrojaya.screens.transaksi.component.AlamatCard
import com.febriandi.agrojaya.ui.theme.CustomFontFamily
import com.febriandi.agrojaya.utils.Resource

@Composable
fun PembelianScreenContent(
    paketState: Resource<PaketResponse>,
    alamatState: Resource<List<AlamatResponse>>,
    onBackClick: () -> Unit,
    navController: NavController,
    onRetry: () -> Unit,
    transaksiViewModel: TransaksiViewModel = androidx.hilt.navigation.compose.hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (paketState) {
            is Resource.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = colorResource(id = R.color.green_500)
                    )
                }
            }
            is Resource.Success -> {
                PembelianDetailContent(
                    paket = paketState.data,
                    alamatState = alamatState,
                    onBackClick = onBackClick,
                    navController = navController,
                    transaksiViewModel = transaksiViewModel
                )
            }
            is Resource.Error -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = paketState.message ?: "Terjadi kesalahan",
                        color = Color.Red,
                        fontFamily = CustomFontFamily
                    )
                    Button(
                        onClick = onRetry,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.green_500)
                        )
                    ) {
                        Text(
                            text = "Coba Lagi",
                            fontFamily = CustomFontFamily,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PembelianDetailContent(
    paket: PaketResponse,
    alamatState: Resource<List<AlamatResponse>>,
    onBackClick: () -> Unit,
    navController: NavController,
    transaksiViewModel: TransaksiViewModel
) {
    val scrollState = rememberScrollState()
    val categories = paket.variasi_bibit
        .replace("\"", "")
        .split(",")
        .map { it.trim() }

    val selectedCategories = remember { mutableStateListOf<String>() }
    var selectedAlamat by remember { mutableStateOf<AlamatResponse?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            ) {
                ButtonBack(onClick = onBackClick)
                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = "Detail Pesanan",
                    fontSize = 16.sp,
                    fontFamily = CustomFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.text_color)
                )
            }

            // Alamat Section
            PembelianAlamatSection(alamatState) { selectedAlamat = it }

            // Paket Details
            PembelianPaketDetails(paket)

            // Varian Bibit Selection
            PembelianVarianBibitSelection(
                categories = categories,
                selectedCategories = selectedCategories
            )

            // Bottom Purchase Section
            PembelianBottomSection(
                paket = paket,
                selectedAlamat = selectedAlamat,
                selectedCategories = selectedCategories,
                navController = navController,
                transaksiViewModel = transaksiViewModel
            )
        }
    }
}