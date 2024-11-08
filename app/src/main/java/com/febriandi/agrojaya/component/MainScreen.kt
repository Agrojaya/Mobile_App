package com.febriandi.agrojaya.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.febriandi.agrojaya.R
import com.febriandi.agrojaya.screens.HomeScreen
import com.febriandi.agrojaya.screens.PaketScreen
import com.febriandi.agrojaya.screens.ProfileScreen
import com.febriandi.agrojaya.ui.theme.CustomFontFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val navItemList = listOf(
        NavItem("Beranda", R.drawable.icon_home),
        NavItem("Paket", R.drawable.icon_paket),
        NavItem("Profile", R.drawable.icon_profile),
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar (
                containerColor = colorResource(id = R.color.green_50)
            ) {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = {
                            Icon(
                                painter = painterResource(id = navItem.icon),
                                contentDescription = navItem.label,
                                tint = if (selectedIndex == index) colorResource(id = R.color.green_400) else colorResource(
                                    id = R.color.natural_900
                                )
                            )
                        },
                        label = {
                            Text(
                                text = navItem.label,
                                color = if (selectedIndex == index) colorResource(id = R.color.green_400) else colorResource(
                                    id = R.color.natural_900
                                ),
                                fontWeight = if (selectedIndex == index) FontWeight.SemiBold else FontWeight.Normal,
                                fontSize = 12.sp,
                                fontFamily = CustomFontFamily
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = colorResource(id = R.color.green_400),
                            unselectedIconColor = colorResource(id = R.color.natural_900),
                            selectedTextColor = colorResource(id = R.color.green_400),
                            unselectedTextColor = colorResource(id = R.color.natural_900),
                            indicatorColor = colorResource(id = R.color.green_50)
                        ),

                    )
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(modifier = Modifier.padding(innerPadding), selectedIndex)
    }
}

@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex: Int) {
    when (selectedIndex) {
        0 -> HomeScreen()
        1 -> PaketScreen()
        2 -> ProfileScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}