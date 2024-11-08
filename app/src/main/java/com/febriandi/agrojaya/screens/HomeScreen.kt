package com.febriandi.agrojaya.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.febriandi.agrojaya.R
import com.febriandi.agrojaya.ui.theme.CustomFontFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    var search by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo3),
                contentDescription = "Logo",
                modifier = Modifier
                    .width(200.dp)
                    .padding(top = 40.dp, end = 30.dp)
                    .align(Alignment.TopEnd)
            )
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 10.dp)
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
            ){
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile Picture",
                    modifier = Modifier.size(70.dp)
                )
                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = "Hi, Adjie Cahya R",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.text_color)
                )
            }
            Button(
                onClick = {
                          
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

        TextField(
            value = search,
            onValueChange = { search = it },
            label = {
                Text(
                    text = "Telusuri",
                    fontFamily = CustomFontFamily
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = colorResource(id = R.color.fill_form),
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
                .padding(vertical = 4.dp, horizontal = 30.dp),
            trailingIcon = {
                IconButton(onClick = { /* Handle search action */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_search), // Ganti dengan resource icon pencarian kamu
                        contentDescription = "Pencarian",
                        tint = colorResource(id = R.color.green_500)
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}