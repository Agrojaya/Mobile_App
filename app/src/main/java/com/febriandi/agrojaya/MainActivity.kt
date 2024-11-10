package com.febriandi.agrojaya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.febriandi.agrojaya.component.MainScreen
import com.febriandi.agrojaya.ui.theme.AgroJayaTheme
import com.febriandi.agrojaya.ui.theme.SplashScreenViewModel
import com.febriandi.agrojaya.onboarding.AfterOnboarding
import com.febriandi.agrojaya.onboarding.OnboardingScreen
import com.febriandi.agrojaya.screens.ArtikelScreen
import com.febriandi.agrojaya.screens.DetailArtikelScreen
import com.febriandi.agrojaya.screens.DetailPaketScreen
import com.febriandi.agrojaya.screens.LoginScreen
import com.febriandi.agrojaya.screens.NotifikasiScreen
import com.febriandi.agrojaya.screens.PembelianScreen
import com.febriandi.agrojaya.screens.RegisterScreen
import com.febriandi.agrojaya.screens.TransaksiStatus

class MainActivity : ComponentActivity() {
    private val splashScreenViewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                splashScreenViewModel.isLoading.value
            }
        }

        setContent {
            AgroJayaTheme {
                val navController = rememberNavController()
                MyAppNavHost(navController = navController)
            }
        }
    }
}

@Composable
fun MyAppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "onboarding"
    ) {
        composable("onboarding") {
            OnboardingScreen(onFinished = {
                navController.navigate("afterOnboarding") {
                    popUpTo("onboarding") { inclusive = true }
                }
            })
        }

        composable(
            route = "pemesanan/{paketId}",
            arguments = listOf(
                navArgument("paketId") {
                    type = NavType.IntType
                    defaultValue = 0  // Added default value
                }
            )
        ) { backStackEntry ->
            PembelianScreen(
                onBackClick = { navController.navigateUp() },
                navController = navController,
                paketId = backStackEntry.arguments?.getInt("paketId")
            )
        }

        composable("afterOnboarding") {
            AfterOnboarding(navController)
        }

        composable("notifikasi") {
            NotifikasiScreen(navController)
        }

        composable("artikel") { ArtikelScreen(navController) }

        composable("transaksi") { TransaksiStatus(navController)}

        composable(
            "detailArtikel/{artikelId}",
            arguments = listOf(
                navArgument("artikelId") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            DetailArtikelScreen(
                navController = navController,
                artikelId = backStackEntry.arguments?.getInt("artikelId")
            )
        }

        composable("login") {
            LoginScreen(navController)
        }

        composable("register") {
            RegisterScreen(navController)
        }

        composable("mainScreen") {
            MainScreen(rootNavController = navController)
        }

        composable(
            "detailPaket/{paketId}",
            arguments = listOf(
                navArgument("paketId") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            DetailPaketScreen(
                navController = navController,
                paketId = backStackEntry.arguments?.getInt("paketId")
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AgroJayaTheme {
        // Add preview content here if needed
    }
}