package com.github.stakkato95.myapplication.presentation.model

import com.github.stakkato95.myapplication.domain.usecase.CombinedUserPost
import javax.inject.Inject

data class PostItem(val postId: String,
                    val title: String,
                    val body: String,
                    val userId: String,
                    val name: String,
                    val username: String,
                    val email: String)

class PostItemMapper @Inject constructor() {

    fun mapToPresentation(cup: CombinedUserPost) = PostItem(
            cup.post.id,
            cup.post.title,
            cup.post.body,
            cup.user.id,
            cup.user.name,
            cup.user.surname,
            cup.user.website
    )

    fun mapToPresentation(cups: List<CombinedUserPost>) = cups.map { mapToPresentation(it) }
}