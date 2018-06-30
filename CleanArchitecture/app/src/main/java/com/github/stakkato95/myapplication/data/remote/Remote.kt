package com.github.stakkato95.myapplication.data.remote

import com.github.stakkato95.myapplication.data.model.Post
import com.github.stakkato95.myapplication.data.model.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsApi {

    @GET("posts/")
    fun getPosts(): Single<List<Post>>

    @GET("posts/{id}")
    fun getPost(@Path("id") postId: String): Single<Post>
}

interface UsersApi {

    @GET("users/")
    fun getUsers(): Single<List<User>>

    @GET("users/{id}")
    fun getUser(@Path("id") userId: String): Single<User>
}