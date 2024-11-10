package com.febriandi.agrojaya.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.febriandi.agrojaya.component.ButtonBack
import com.febriandi.agrojaya.component.NotifikasiItem
import com.febriandi.agrojaya.data.DummyData
import com.febriandi.agrojaya.model.Notifikasi
import com.febriandi.agrojaya.ui.theme.CustomFontFamily

@Composable
fun NotifikasiScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    notifikasis: List<Notifikasi> = DummyData.notifikasi,
){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 20.dp)
        ) {
            ButtonBack {
                navController.popBackStack()
            }
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = "Notifikasi",
                fontSize = 16.sp,
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.text_color)
            )
        }
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
        ){
            items(notifikasis, key = { it.id}) {
                NotifikasiItem(notifikasi = it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotifikasiScreenPreview(){
    val navController = rememberNavController()
    NotifikasiScreen(navController = navController)
}