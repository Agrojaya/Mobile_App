package com.febriandi.agrojaya.main

import JadwalAktivitasScreen
import TambahAlamat
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.febriandi.agrojaya.screens.register.AfterOnboarding
import com.febriandi.agrojaya.screens.onboarding.OnboardingScreen
import com.febriandi.agrojaya.screens.artikel.ArtikelScreen
import com.febriandi.agrojaya.screens.artikel.DetailArtikelScreen
import com.febriandi.agrojaya.screens.Paket.DetailPaketScreen
import com.febriandi.agrojaya.screens.login.LoginScreen
import com.febriandi.agrojaya.screens.register.RegisterScreen
import com.febriandi.agrojaya.screens.transaksi.TransaksiStatus
import com.febriandi.agrojaya.screens.alamat.AlamatScreen
import com.febriandi.agrojaya.screens.alamat.UpdateAlamatScreen
import com.febriandi.agrojaya.screens.login.ForgotPasswordScreen
import com.febriandi.agrojaya.screens.login.GantiPasswordScreen
import com.febriandi.agrojaya.screens.login.LoginViewModel
import com.febriandi.agrojaya.screens.notifikasi.NotifikasiScreen
import com.febriandi.agrojaya.screens.pembelian.PembelianScreen
import com.febriandi.agrojaya.screens.pengaturan.KebijakanPrivasiScreen
import com.febriandi.agrojaya.screens.pengaturan.KontakKamiScreen
import com.febriandi.agrojaya.screens.pengaturan.SyaratDanKetentuanScreen
import com.febriandi.agrojaya.screens.pengingat.TambahPengingatScreen
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
            NotifikasiScreen(navController = navController)
        }

        composable("tambahPengingat") {
            TambahPengingatScreen(navController = navController)
        }

        composable(
            "tambahPengingat/{pengingatId}",
            arguments = listOf(navArgument("pengingatId") { type = NavType.IntType })
        ) { backStackEntry ->
            val pengingatId = backStackEntry.arguments?.getInt("pengingatId")
            TambahPengingatScreen(
                navController = navController,
                pengingatId = pengingatId
            )
        }

        composable("jadwalAktivitas") {
            JadwalAktivitasScreen(navController = navController)
        }

        composable("syaratdanketentuan") {
            SyaratDanKetentuanScreen(rootNavController = navController)
        }

        composable("kebijakanprivasi") {
            KebijakanPrivasiScreen(rootNavController = navController)
        }

        composable("kontakKami") {
            KontakKamiScreen(rootNavController = navController, context = LocalContext.current)
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

        composable(
            "ubahAlamat/{alamatId}",
            arguments = listOf(
                navArgument("alamatId") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("alamatId")?.let {
                UpdateAlamatScreen(
                    navController = navController,
                    alamatId = it
                )
            }
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