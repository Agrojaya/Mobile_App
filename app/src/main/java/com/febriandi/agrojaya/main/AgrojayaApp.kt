package com.febriandi.agrojaya.main

import TambahAlamat
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.febriandi.agrojaya.screens.register.AfterOnboarding
import com.febriandi.agrojaya.onboarding.OnboardingScreen
import com.febriandi.agrojaya.screens.artikel.ArtikelScreen
import com.febriandi.agrojaya.screens.artikel.DetailArtikelScreen
import com.febriandi.agrojaya.screens.Paket.DetailPaketScreen
import com.febriandi.agrojaya.screens.login.LoginScreen
import com.febriandi.agrojaya.screens.NotifikasiScreen
import com.febriandi.agrojaya.screens.register.RegisterScreen
import com.febriandi.agrojaya.screens.transaksi.TransaksiStatus
import com.febriandi.agrojaya.screens.alamat.AlamatScreen
import com.febriandi.agrojaya.screens.login.ForgotPasswordScreen
import com.febriandi.agrojaya.screens.login.GantiPasswordScreen
import com.febriandi.agrojaya.screens.login.LoginViewModel
import com.febriandi.agrojaya.screens.pembelian.PembelianScreen
import com.febriandi.agrojaya.screens.profile.EditProfileScreen
import com.febriandi.agrojaya.screens.transaksi.DaftarTransaksiScreen
import com.febriandi.agrojaya.screens.transaksi.DetailTransaksi
import com.febriandi.agrojaya.screens.transaksi.PaymentWebViewScreen

@Composable
fun AgrojayaApp(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val isLoggedIn by viewModel.isUserLoggedIn.collectAsState()
    val navController = rememberNavController()

    // Handle navigation based on login state
    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            navController.navigate("mainScreen") {
                popUpTo(0) { inclusive = true }
            }
        }
    }

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
                    defaultValue = 0
                }
            )
        ) { backStackEntry ->
            PembelianScreen(
                onBackClick = { navController.navigateUp() },
                navController = navController,
                paketId = backStackEntry.arguments?.getInt("paketId")
            )
        }

        composable(
            route = "payment-webview/{paymentUrl}/{orderId}",
            arguments = listOf(
                navArgument("paymentUrl") {
                    type = NavType.StringType
                    nullable = false
                },
                navArgument("orderId") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val paymentUrl = backStackEntry.arguments?.getString("paymentUrl") ?: ""
            val orderId = backStackEntry.arguments?.getString("orderId") ?:""
                PaymentWebViewScreen(
                    navController = navController,
                    paymentUrl = paymentUrl,
                    orderId = orderId
                )
        }
        composable("afterOnboarding") {
            AfterOnboarding(navController)
        }

        composable("notifikasi") {
            NotifikasiScreen(navController)
        }

        composable("artikel") {
            ArtikelScreen(navController)
        }


        composable("alamat") {
            AlamatScreen(navController = navController)
        }

        composable("daftarTransaksi") {
            DaftarTransaksiScreen(
                navController = navController)
        }

        composable("tambahAlamat") {
            TambahAlamat(navController = navController)
        }

        composable("gantiPassword") {
            GantiPasswordScreen(navController = navController)
        }

        composable("editProfile") {
            EditProfileScreen(navController = navController)
        }

        composable("lupaPassword") {
            ForgotPasswordScreen(navController = navController,
                onResetSuccess = {navController.popBackStack()})
        }

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
                rootNavController = navController,
                navController = navController,
                artikelId = backStackEntry.arguments?.getInt("artikelId")
            )
        }

        composable(
            "transaksi/{orderId}",
            arguments = listOf(
                navArgument("orderId") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            TransaksiStatus(
                rootNavController = navController,
                navController = navController,
                orderId = backStackEntry.arguments?.getString("orderId")
            )
        }

        composable(
            "detailTransaksi/{orderId}",
            arguments = listOf(
                navArgument("orderId") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            DetailTransaksi(
                navController = navController,
                orderId = backStackEntry.arguments?.getString("orderId")
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