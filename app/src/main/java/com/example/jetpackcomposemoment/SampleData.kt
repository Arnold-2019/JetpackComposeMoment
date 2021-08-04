package com.example.jetpackcomposemoment

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class SampleData {
    fun tweetsSample(): List<Message> {
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
            Message("John Doe", "What a sunny day."),
            Message("Laura", "I am beautiful!"),
            Message("Arnold", "I am strong!"),
            Message("John Doe", "What a sunny day."),
            Message("Laura", "I am beautiful!"),
            Message("Arnold", "I am strong!"),
            Message("John Doe", "What a sunny day."),
            Message("Laura", "I am beautiful!"),
            Message("Arnold", "I am strong!"),
            Message("John Doe", "What a sunny day."),
            Message("Laura", "I am beautiful!"),
            Message("Arnold", "I am strong!"),
            Message("John Doe", "What a sunny day."),
            Message("Laura", "I am beautiful!"),
            Message("Arnold", "I am strong!"),
            Message("John Doe", "What a sunny day."),
            Message("Laura", "I am beautiful!"),
            Message("Arnold", "I am strong!"),
        )
    }

    fun nickNameSample(): String {
        return userProfile.nick
    }

    private val profileJsonString = "{\n" +
            "  \"profile-image\": \"https://thoughtworks-mobile-2018.herokuapp.com/images/user/profile-image.jpg\",\n" +
            "  \"avatar\": \"https://thoughtworks-mobile-2018.herokuapp.com/images/user/avatar.png\",\n" +
            "  \"nick\": \"Huan Huan\",\n" +
            "  \"username\": \"hengzeng\"\n" +
            "}"

    data class UserProfile(
        @SerializedName("profile-image")
        val profileImage: String,
        val avatar: String,
        val nick: String,
        val username: String
    )

    private val userProfile: UserProfile = Gson().fromJson(profileJsonString, UserProfile::class.java)
}
