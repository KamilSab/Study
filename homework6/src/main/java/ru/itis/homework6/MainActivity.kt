package ru.itis.homework6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.itis.homework6.ui.view.AddFilmScreen
import ru.itis.homework6.ui.view.LoginScreen
import ru.itis.homework6.ui.view.MainScreen
import ru.itis.homework6.ui.view.RegistrationScreen
import viewmodel.AuthViewModel
import viewmodel.FilmViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val authViewModel: AuthViewModel = viewModel()
            val navController = rememberNavController()
            val startDestination = if (authViewModel.isLoggedIn) "main" else "login"

            NavHost(navController = navController, startDestination = startDestination) {
                composable("login") {
                    LoginScreen(
                        onLoginSuccess = {
                            navController.navigate("main") {
                                popUpTo("login") { inclusive = true }
                            }
                        },
                        onNavigateToRegister = { navController.navigate("register") },
                        authViewModel = authViewModel
                    )
                }
                composable("register") {
                    RegistrationScreen(
                        onRegisterSuccess = {
                            navController.navigate("main") {
                                popUpTo("register") { inclusive = true }
                            }
                        },
                        authViewModel = authViewModel
                    )
                }
                composable("main") {
                    val filmViewModel: FilmViewModel = viewModel()
                    MainScreen(
                        onLogout = {
                            authViewModel.logout()
                            navController.navigate("login") {
                                popUpTo("main") { inclusive = true }
                            }
                        },
                        onNavigateToAdd = { navController.navigate("addFilm") },
                        filmViewModel = filmViewModel
                    )
                }
                composable("addFilm") {
                    val filmViewModel: FilmViewModel = viewModel()
                    AddFilmScreen(
                        onFilmAdded = { navController.popBackStack() },
                        filmViewModel = filmViewModel
                    )
                }
            }
        }
    }
}