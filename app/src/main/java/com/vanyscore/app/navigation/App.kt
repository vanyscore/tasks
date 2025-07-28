package com.vanyscore.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vanyscore.app.MainScreen
import com.vanyscore.app.ui.AppBottomBar
import com.vanyscore.notes.NoteScreen
import com.vanyscore.notes.domain.Note
import com.vanyscore.settings.SettingsScreen

var LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("No navController provided")
}

@Composable
fun App() {
    val navController = rememberNavController()

    CompositionLocalProvider(
        LocalNavController provides navController
    ) {
        Scaffold(
            bottomBar = {
                AppBottomBar()
            },
            containerColor = MaterialTheme.colorScheme.primary
        ) { padding ->
            NavHost(
                modifier = Modifier.padding(padding),
                navController = LocalNavController.current,
                startDestination = AppRoutes.MAIN
            ) {
                composable(AppRoutes.MAIN) {
                    MainScreen()
                }
                composable(AppRoutes.NOTE, listOf(
                    navArgument(AppRouteArgs.NOTE_ID) {
                        type = NavType.IntType
                        defaultValue = -1
                    }
                )) {
                    val noteId = it.arguments?.getInt(AppRouteArgs.NOTE_ID)
                    NoteScreen(if (noteId == -1) null else noteId)
                }
                composable(AppRoutes.SETTINGS) {
                    SettingsScreen()
                }
            }
        }
    }
}

fun NavController.openSettings() {
    navigate(AppRoutes.SETTINGS)
}

fun NavController.openNote(note: Note?) {
    navigate(AppRoutSchemes.note(note?.id))
}