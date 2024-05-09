package com.example.youtubeclone

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun VideoThumbnail(
    modifier: Modifier = Modifier,
    video: Video,
    onItemClicked: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClicked(video.video) },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(video.thumbnail)
                .crossfade(true)
                .build(),
            contentDescription = "Video Thumbnail",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Spacer(modifier = Modifier.width(30.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(video.thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = "Channel Icon",
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(35.dp),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = video.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )

                Text(text = "${video.channel} | ${video.views}K views | ${video.time} days ago",
                    style = MaterialTheme.typography.bodySmall)
            }

            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Menu",
                modifier = Modifier.padding(end = 20.dp)
                    .size(20.dp)
            )
        }
    }
}