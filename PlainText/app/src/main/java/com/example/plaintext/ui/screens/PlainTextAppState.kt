package com.example.plaintext.ui.screens

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.plaintext.data.model.PasswordInfo
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {

    @Serializable
    object Login

    @Serializable
    data class Hello(
        val name: String?
    )

    @Serializable
    object Preferences

    @Serializable
    object List

    @Serializable
    data class EditList(
        val password: PasswordInfo,
        val title: String
    )

    @Serializable
    object Sensors
}

@Composable
fun rememberPlainTextAppState(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) = remember(navController, context) {
    PlainTextAppState(
        navController = navController,
        context = context
    )
}

class PlainTextAppState(
    val navController: NavHostController,
    private val context: Context
) {

    fun checkRoute(route: String): Boolean {
        val currentRoute =
            navController.currentBackStackEntry?.destination?.route.toString()

        return currentRoute != route
    }

    fun navigateToHello(name: String?) {
        navController.navigate(
            Screen.Hello(name)
        )
    }

    fun navigateToLogin() {
        navController.navigate(
            Screen.Login
        )
    }

    fun navigateToPreferences() {
        navController.navigate(
            Screen.Preferences
        )
    }

    fun navigateToList() {
        navController.navigate(
            Screen.List
        ) {
            popUpTo(Screen.Login) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    fun navigateToEditList(
        password: PasswordInfo,
        title: String
    ) {
        navController.navigate(
            Screen.EditList(
                password = password,
                title = title
            )
        )
    }

    fun navigateBack() {
        navController.popBackStack()
    }
}
