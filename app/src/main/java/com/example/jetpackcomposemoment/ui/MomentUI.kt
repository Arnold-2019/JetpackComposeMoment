package com.example.jetpackcomposemoment.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import com.example.jetpackcomposemoment.data.Tweet
import com.example.jetpackcomposemoment.data.UserProfile
import com.example.jetpackcomposemoment.data.allTweetsJsonString
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
        val profileImageUrl = userProfile.profileImage
        val avatarUrl = userProfile.avatar

        Image(
            modifier = Modifier
                .fillMaxSize()
                .height(270.dp)
                .constrainAs(profileImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.TopStart,
            //TODO: replace with default image when loading failed
            painter = rememberImagePainter(profileImageUrl),
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
            painter = rememberImagePainter(avatarUrl),
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
    val photoUrls: List<String> = tweet.images?.let { urls ->
        urls.map { it.url }
    } ?: listOfNotNull()

    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(4.dp)),
            painter = rememberImagePainter(avatarUrl),
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
            Spacer(modifier = Modifier.width(4.dp))

            GridView(imageUrls = photoUrls)
        }
    }
}

@Composable
fun GridView(imageUrls: List<String>) {
    val numOfImages = imageUrls.size
    val imageSize: Int = when (numOfImages) {
        0 -> 0
        1 -> 150
        4 -> 100
        else -> 80
    }

    when (numOfImages) {
        in 1 until 4 -> {
            CustomizedRow(imageUrls.subList(0, numOfImages), imageSize)
        }
        4 -> {
            Column {
                CustomizedRow(imageUrls.subList(0, 2), imageSize)
                CustomizedRow(imageUrls.subList(2, 4), imageSize)
            }
        }
        5, 6 -> {
            Column {
                CustomizedRow(imageUrls.subList(0, 3), imageSize)
                CustomizedRow(imageUrls.subList(3, numOfImages), imageSize)
            }
        }

        in 7 until 10 -> {
            Column {
                CustomizedRow(imageUrls.subList(0, 3), imageSize)
                CustomizedRow(imageUrls.subList(3, 6), imageSize)
                CustomizedRow(imageUrls.subList(6, numOfImages), imageSize)
            }
        }
    }
}

@Composable
fun CustomizedRow(imageUrls: List<String>, imageSize: Int) {
    LazyRow(modifier = Modifier.padding(all = 4.dp)) {
        items(imageUrls) { imageUrl ->
            Image(
                modifier = Modifier.size(imageSize.dp),
                painter = rememberImagePainter(imageUrl),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun ComposablePreview() {
    val userProfile = getSampleUserProfile()
    val tweets = getSampleAllTweets(allTweetsJsonString)
    MomentScreen(userProfile, tweets)
}
