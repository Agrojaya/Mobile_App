package com.febriandi.agrojaya


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.febriandi.agrojaya.component.MainScreen
import com.febriandi.agrojaya.ui.theme.AgroJayaTheme
import com.febriandi.agrojaya.ui.theme.SplashScreenViewModel
import com.febriandi.agrojaya.onboarding.AfterOnboarding
import com.febriandi.agrojaya.onboarding.OnboardingScreen
import com.febriandi.agrojaya.screens.LoginScreen
import com.febriandi.agrojaya.screens.RegisterScreen

class MainActivity : ComponentActivity() {
    private val splashScreenViewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Install splash screen and keep it until loading is complete
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                splashScreenViewModel.isLoading.value
            }
        }

        setContent {
            AgroJayaTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "onboarding") {
                   composable("onboarding") { OnboardingScreen(onFinished = {
                       navController.navigate("afterOnboarding")
                   })}
                    composable("afterOnboarding") { AfterOnboarding(navController)  }
                    composable("login") { LoginScreen(navController) }
                    composable("register") { RegisterScreen(navController)  }
                    composable("mainScreen") { MainScreen()  }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AgroJayaTheme {
    }
}
