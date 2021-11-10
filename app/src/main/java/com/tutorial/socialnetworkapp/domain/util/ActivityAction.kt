package com.tutorial.socialnetworkapp.domain.util

sealed class ActivityAction{
    object LikedPost: ActivityAction()
    object CommentedOnPost: ActivityAction()
    object FollowedYou: ActivityAction()
}
