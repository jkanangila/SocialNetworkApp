package com.tutorial.socialnetworkapp.domain.models

import com.tutorial.socialnetworkapp.domain.util.ActivityAction

data class Activity(
    val username: String,
    val actionType: ActivityAction,
    val formattedTime: String
)
