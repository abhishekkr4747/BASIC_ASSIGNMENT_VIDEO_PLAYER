package com.example.youtubeclone.Navigation


enum class AppScreens {
    HomeScreen,
    VideoScreen;
    companion object {
        fun fromRoute(route: String?) : AppScreens
                = when (route?.substringBefore("/")) {
            HomeScreen.name -> HomeScreen
            VideoScreen.name -> VideoScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized.")
        }

    }
}