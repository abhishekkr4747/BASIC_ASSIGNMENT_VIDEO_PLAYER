package com.example.youtubeclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.youtubeclone.Navigation.AppNavigation
import com.example.youtubeclone.ui.theme.YoutubeCloneTheme
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest



val supabase = createSupabaseClient(
    supabaseUrl = "https://ltrimpldfjdhnksetrba.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imx0cmltcGxkZmpkaG5rc2V0cmJhIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTUxNjI3MTUsImV4cCI6MjAzMDczODcxNX0.x6jJY9BElqf1zvNvlp1YdtscLvVb0-Qiuc8AXKJqj6E"
) {
    install(Postgrest)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp{
                AppNavigation()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    YoutubeCloneTheme {
        content()
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApp {
        AppNavigation()
    }
}