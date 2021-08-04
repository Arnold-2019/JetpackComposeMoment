package com.example.jetpackcomposemoment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
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
fun ProfileCard() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (profileImage, avatar, nickName) = createRefs()

        Image(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(profileImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.TopStart,
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "User profile image."
        )

        Image(
            modifier = Modifier
                .constrainAs(avatar) {
                    top.linkTo(profileImage.bottom)
                    bottom.linkTo(profileImage.bottom)
                    end.linkTo(profileImage.end)
                },
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "avatar"
        )

        Text(
            modifier = Modifier
                .constrainAs(nickName) {
                    bottom.linkTo(profileImage.bottom)
                    end.linkTo(avatar.start)
                }
                .padding(4.dp),
            text = SampleData().nickNameSample()
        )
    }
}

@Composable
fun TweetList(messages: List<Message>) {
    LazyColumn {
        item { ProfileCard() }
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
