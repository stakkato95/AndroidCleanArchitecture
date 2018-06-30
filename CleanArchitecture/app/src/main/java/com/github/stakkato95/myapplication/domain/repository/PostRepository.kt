package com.github.stakkato95.myapplication.domain.repository

import com.github.stakkato95.myapplication.data.model.Post
import io.reactivex.Single

interface PostRepository {

    val key: String

    fun get(refresh: Boolean): Single<List<Post>>

    fun get(postId: String, refresh: Boolean): Single<Post>
}