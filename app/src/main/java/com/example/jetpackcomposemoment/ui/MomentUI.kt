package com.example.jetpackcomposemoment.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpackcomposemoment.R
import com.example.jetpackcomposemoment.data.Tweet
import com.example.jetpackcomposemoment.data.UserProfile
import com.example.jetpackcomposemoment.data.getSampleAllTweets
import com.example.jetpackcomposemoment.data.getSampleUserProfile

@Composable
fun MomentScreen(userProfile: UserProfile, tweets: List<Tweet>) {
    LazyColumn {
        item { Profile(userProfile = userProfile) }
        items(tweets) { tweet ->
            TweetCard(tweet = tweet)
        }
    }
}

@Composable
fun Profile(userProfile: UserProfile) {
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
            //TODO: replace with default image when loading failed
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "User profile image."
        )

        Image(
            modifier = Modifier
                .size(width = 100.dp, height = 100.dp)
                .constrainAs(avatar) {
                    top.linkTo(profileImage.bottom)
                    bottom.linkTo(profileImage.bottom)
                    end.linkTo(profileImage.end)
                }
                .padding(end = 16.dp),
            //TODO: replace with default image when loading failed
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "avatar"
        )

        Text(
            modifier = Modifier
                .constrainAs(nickName) {
                    bottom.linkTo(profileImage.bottom)
                    end.linkTo(avatar.start)
                }
                .padding(all = 4.dp),
            text = userProfile.nick,
            style = TextStyle(fontSize = 20.sp)
        )
    }
}

@Composable
fun TweetCard(tweet: Tweet) {
    val avatarUrl = tweet.sender?.avatarUrl
    val nickname = tweet.sender?.nick ?: "Nick Name"
    val tweetContent = tweet.content ?: ""

    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(0.5.dp, MaterialTheme.colors.secondary, RectangleShape),
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Avatar"
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = nickname,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.width(4.dp))

            Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
                Text(
                    text = tweetContent,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Preview
@Composable
fun ComposablePreview() {
    val userProfile = getSampleUserProfile()
    val tweets = getSampleAllTweets()
    MomentScreen(userProfile, tweets)
}
