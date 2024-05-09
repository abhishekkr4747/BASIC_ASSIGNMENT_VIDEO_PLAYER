package com.example.youtubeclone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.youtubeclone.Navigation.AppScreens
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Serializable
data class Video (
    val id: Int,
    val title: String,
    val channel: String,
    val description: String,
    val views: Int,
    val time: Int,
    val thumbnail: String,
    val video: String
)

@Composable
fun HomeScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                searchText = searchText,
                onSearchTextChanged = { searchText = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            VideosList(searchText = searchText, navController = navController)
        }
    }
}



@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchText: String,
    onSearchTextChanged: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = searchText,
        onValueChange = { onSearchTextChanged(it) },
        label = { Text("Search") }
    )
}


@Composable
fun VideosList(searchText: String, navController: NavController) {
    val videos = remember { mutableStateListOf<Video>() }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            val results = supabase.from("videos").select().decodeList<Video>()
            videos.addAll(results)
        }
    }

    val filteredVideos = if (searchText.isBlank()) {
        videos
    } else {
        videos.filter { it.title.contains(searchText, ignoreCase = true) }
    }

    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(filteredVideos) { video ->
            VideoThumbnail(video = video) {
                val videos = video.video
                val encode = URLEncoder.encode(videos, StandardCharsets.UTF_8.toString())
                navController.navigate(AppScreens.VideoScreen.name+"/$encode")
            }
        }
    }
}