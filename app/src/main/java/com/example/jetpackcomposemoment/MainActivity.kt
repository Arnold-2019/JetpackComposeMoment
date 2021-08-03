package com.example.jetpackcomposemoment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposemoment.ui.theme.JetpackComposeMomentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeMomentTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    TweetList(messages = SampleData().tweetsSample())
                }
            }
        }
    }
}

data class Message(val author: String, val body: String)
class SampleData {
    fun tweetsSample() : List<Message> {
        return listOf(
            Message("John Doe", "What a sunny day."),
            Message("Laura", "I am beautiful!"),
            Message("Arnold", "I am strong!"),
            Message("John Doe", "What a sunny day."),
            Message("Laura", "I am beautiful!"),
            Message("Arnold", "I am strong!"),
            Message("John Doe", "What a sunny day."),
            Message("Laura", "I am beautiful!"),
            Message("Arnold", "I am strong!"),
            Message("Arnold", "I am strong!"),
            Message("Arnold", "I am strong!"),
            Message("Arnold", "I am strong!")
        )
    }
}

@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(0.5.dp, MaterialTheme.colors.secondary, CircleShape),
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "My avatar"
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.width(4.dp))

            Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
fun TweetList(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(msg = message)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTweetList() {
    JetpackComposeMomentTheme {
        TweetList(messages = SampleData().tweetsSample())
    }
}
