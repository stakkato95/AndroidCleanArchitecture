package com.github.stakkato95.myapplication.presentation.model

import javax.inject.Inject

data class PostItem(val postId: String,
                    val title: String,
                    val body: String,
                    val name: String,
                    val userId: String,
                    val username: String,
                    val email: String)

class PostItemMapper @Inject constructor() {

}