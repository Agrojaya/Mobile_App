package com.febriandi.agrojaya.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.febriandi.agrojaya.R
import com.febriandi.agrojaya.screens.login.LoginViewModel
import com.febriandi.agrojaya.ui.theme.CustomFontFamily
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(
    navController: NavController,
    rootNavController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val currentUser = FirebaseAuth.getInstance().currentUser
    val displayName = currentUser?.email?.substringBefore("@") ?: "N/A"
    val email = currentUser?.email ?: "N/A"

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
                text = displayName,
                fontSize = 14.sp,
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.text_color)

            )

            Text(
                text = email,
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
        Column (
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(colorResource(id = R.color.green_100))
                .padding(12.dp)
                .height(50.dp)
                .clickable {

                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.box),
                    contentDescription = null,
                    tint = colorResource(id = R.color.green_400),
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "Paket Saya",
                        color = colorResource(id = R.color.text_color),
                        fontSize = 14.sp,
                        fontFamily = CustomFontFamily,
                        fontWeight = FontWeight.Medium
                    )

                        Text(
                            text = "Lihat transaksi anda di sini",
                            color = Color.Gray,
                            fontSize = 12.sp,
                            fontFamily = CustomFontFamily
                        )

                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column (
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(colorResource(id = R.color.green_100))
                .padding(12.dp)
                .height(50.dp)
                .clickable {
                    rootNavController.navigate("alamat")
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.loc),
                    contentDescription = null,
                    tint = colorResource(id = R.color.green_400),
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "Alamat Pengguna",
                        color = colorResource(id = R.color.text_color),
                        fontSize = 14.sp,
                        fontFamily = CustomFontFamily,
                        fontWeight = FontWeight.Medium
                    )

                    Text(
                        text = "Atur alamat pemasangan urban farming",
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontFamily = CustomFontFamily
                    )

                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Pengaturan Akun",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = CustomFontFamily,
            color = colorResource(id = R.color.text_color)
        )

        Spacer(modifier = Modifier.height(12.dp))

        MenuButton(
            icon = R.drawable.lock,
            text = "Keamanan Akun",
            subtitle = "Ubah kata sandi",
            onClick = {

            }
        )

        MenuButton(
            icon = R.drawable.doc,
            text = "Syarat dan Ketentuan",
            onClick = {

            }
        )

        MenuButton(
            icon = R.drawable.privacy,
            text = "Kebijakan Privasi",
            onClick = {

            }

        )

        MenuButton(
            icon = R.drawable.call,
            text = "Kontak Kami",
            onClick = {

            }
        )

        MenuButton(
            icon = R.drawable.logout,
            text = "Keluar Akun",
            onClick = {
                viewModel.signOut {
                    rootNavController.navigate("onboarding") {
                        popUpTo(rootNavController.graph.startDestinationId) { inclusive = true }
                    }
                }
            }
        )


        Spacer(modifier = Modifier.height(24.dp))
    }
}


@Composable
fun MenuButton(
    icon: Int,
    text: String,
    subtitle: String? = null,
    backgroundColor: Color = Color.Transparent,
    textColor: Color = colorResource(id = R.color.text_color),
    onClick: () -> Unit

) {
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(6.dp)
            .height(40.dp)
            .clickable(onClick = onClick)

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

