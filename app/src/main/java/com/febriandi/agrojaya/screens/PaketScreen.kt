package com.febriandi.agrojaya.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.febriandi.agrojaya.R
import com.febriandi.agrojaya.component.PaketItem
import com.febriandi.agrojaya.data.DummyData
import com.febriandi.agrojaya.model.Paket
import com.febriandi.agrojaya.ui.theme.CustomFontFamily

@Composable
fun  PaketScreen(
    navController: NavController,
    rootNavController: NavController,
    modifier: Modifier = Modifier,
    pakets: List<Paket> = DummyData.paket,
){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Text(
            modifier = Modifier
                .padding(20.dp, 20.dp, 20.dp),
            text = "Daftar Paket",
            fontSize = 16.sp,
            fontFamily = CustomFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.text_color)
        )
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
        ){
            items(
                pakets, key = { it.id }) {
                PaketItem(paket = it) { paketId ->
                    rootNavController.navigate("detailPaket/$paketId")
                }
            }
        }
    }
}

