package com.febriandi.agrojaya.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.febriandi.agrojaya.R
import com.febriandi.agrojaya.component.ArtikelItem
import com.febriandi.agrojaya.component.ButtonBack
import com.febriandi.agrojaya.data.DummyData
import com.febriandi.agrojaya.data.DummyData.artikel
import com.febriandi.agrojaya.model.Artikel
import com.febriandi.agrojaya.ui.theme.CustomFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtikelScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    artikels: List<Artikel> = DummyData.artikel,
) {
    var search by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header with back button
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
                text = "Artikel",
                fontSize = 16.sp,
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.text_color)
            )
        }

        // Search section
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = "Cari artikel menarik di sini",
                fontSize = 14.sp,
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.text_color)
            )

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

        // Articles list
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(
                artikels, key = { it.id }) {
                ArtikelItem(artikel = it) { artikelId ->
                    navController.navigate("detailArtikel/$artikelId")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtikelScreenPreview() {
    val navController = rememberNavController()
    ArtikelScreen(navController = navController)
}