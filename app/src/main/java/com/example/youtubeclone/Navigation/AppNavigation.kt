package com.example.youtubeclone.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.youtubeclone.HomeScreen
import com.example.youtubeclone.VideoScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = AppScreens.HomeScreen.name ) {

        composable(AppScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }

        composable(AppScreens.VideoScreen.name+"/{video}",
            arguments = listOf(
                navArgument(name = "video") {
                    type = NavType.StringType
                }
            )
        ) { it ->
            it.arguments?.getString("video")?.let {video->
                VideoScreen(video = video)
            }
        }
    }
}

