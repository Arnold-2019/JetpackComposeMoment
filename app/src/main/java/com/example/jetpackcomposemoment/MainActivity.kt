package com.example.jetpackcomposemoment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposemoment.data.getSampleAllTweets
import com.example.jetpackcomposemoment.data.getSampleUserProfile
import com.example.jetpackcomposemoment.ui.theme.JetpackComposeMomentTheme
import com.example.jetpackcomposemoment.ui.MomentScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeMomentTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MomentScreen(
                        userProfile = getSampleUserProfile(),
                        tweets = getSampleAllTweets()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTweetList() {
    JetpackComposeMomentTheme {
        MomentScreen(userProfile = getSampleUserProfile(), tweets = getSampleAllTweets())
    }
}
