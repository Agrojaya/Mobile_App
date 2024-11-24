package com.febriandi.agrojaya.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.febriandi.agrojaya.R
import com.febriandi.agrojaya.ui.theme.CustomFontFamily

@Composable
fun ProfileScreen() {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 20.dp)
            .verticalScroll(scrollState)
    ) {
        // Logo
        Box(
            modifier = Modifier
                .fillMaxWidth()
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

        // Profile Header
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .border(2.dp, Color(0xFF4CAF50), CircleShape)
                    .padding(3.dp)
                    .clip(CircleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile Picture",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Adjie Cahya Ramadhan",
                fontSize = 14.sp,
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "adjiecahya05@gmail.com",
                fontSize = 12.sp,
                fontFamily = CustomFontFamily,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.green_400)
                ),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier.width(150.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_edit),
                    contentDescription = "Google Icon",
                    modifier = Modifier.size(20.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Edit Profile",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.white)
                )
            }
        }

        // Menu Items
        MenuButton(
            icon = R.drawable.icon_box,
            text = "Pesanan Saya",
            backgroundColor = Color(0xFFE8F5E9),
            modifier = Modifier
                .height(200.dp),
            onClick = {

            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        MenuButton(
            icon = R.drawable.icon_lokasi,
            text = "Alamat Pengguna",
            subtitle = "Atur alamat pemasangan urban farming",
            backgroundColor = Color(0xFFE8F5E9),
            onClick = {

            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Pengaturan Akun",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = CustomFontFamily
        )

        Spacer(modifier = Modifier.height(12.dp))

        MenuButton(
            icon = R.drawable.icon_gembok,
            text = "Keamanan Akun",
            subtitle = "Ubah kata sandi",
            onClick = {

            }
        )

        MenuButton(
            icon = R.drawable.icon_text,
            text = "Syarat dan Ketentuan",
            onClick = {

            }
        )

        MenuButton(
            icon = R.drawable.icon_privasi,
            text = "Kebijakan Privasi",
            onClick = {

            }

        )

        MenuButton(
            icon = R.drawable.icon_telepon,
            text = "Kontak Kami",
            onClick = {

            }
        )

        MenuButton(
            icon = R.drawable.icon_keluar,
            text = "Keluar Akun",
            onClick = {

            }
        )
    }
}


@Composable
fun MenuButton(
    icon: Int,
    text: String,
    subtitle: String? = null,
    backgroundColor: Color = Color.Transparent,
    textColor: Color = colorResource(id = R.color.text_color),
    onClick: () -> Unit,
    modifier: Modifier = Modifier

) {
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(12.dp)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = colorResource(id = R.color.green_400),
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = text,
                    color = textColor,
                    fontSize = 14.sp,
                    fontFamily = CustomFontFamily,
                    fontWeight = FontWeight.Medium
                )
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontFamily = CustomFontFamily
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}