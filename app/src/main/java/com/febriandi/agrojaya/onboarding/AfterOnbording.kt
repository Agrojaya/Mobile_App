package com.febriandi.agrojaya.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.febriandi.agrojaya.R
import androidx.compose.foundation.border
import com.febriandi.agrojaya.component.ButtonComponent
import androidx.compose.material3.Text as Text

@Composable
fun AfterOnboarding(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(150.dp))

        Image(
            painter = painterResource(id = R.drawable.logo2),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 90.dp),
            alignment = Alignment.Center
        )

        Spacer(modifier = Modifier.size(60.dp))

        ButtonComponent(
            text = "Masuk Akun",
            onClick = {
                navController.navigate("login")
            }
        )


        ButtonComponent(
            text = "Daftar Akun",
            onClick = {
                navController.navigate("register")
            }
        )
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Divider(
                color = colorResource(id = R.color.natural_500 ),
                thickness = 1.dp,
                modifier = Modifier
                    .weight(1f)
                    .padding(30.dp, 0.dp, 0.dp, 0.dp)
            )
            Text(
                text = "Atau Masuk Dengan",
                fontSize = 14.sp,
                color = colorResource(id = R.color.text_color ),
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Divider(
                color = colorResource(id = R.color.natural_500 ),
                thickness = 1.dp,
                modifier = Modifier
                    .weight(1f)
                    .padding(0.dp, 0.dp, 30.dp, 0.dp)
            )
        }

        Spacer(modifier = Modifier.height(35.dp))


        Button(
            onClick = {
                // Handle Google login
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent // Transparent background
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .height(42.dp)
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.natural_200), // Border color
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_google),
                contentDescription = "Google Icon",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Masuk Dengan Google",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = colorResource(id = R.color.text_color)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AfterOnboardingPreview() {
    // Use rememberNavController to provide a NavController in the preview
    val navController = rememberNavController()
    AfterOnboarding(navController = navController)
}
