package com.github.stakkato95.myapplication.data.repository

import com.github.stakkato95.myapplication.data.cache.Cache
import com.github.stakkato95.myapplication.data.model.Post
import com.github.stakkato95.myapplication.data.remote.PostsApi
import com.github.stakkato95.myapplication.domain.repository.PostRepository
import io.reactivex.Single
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val api: PostsApi,
                                             private val cache: Cache<List<Post>>) : PostRepository {

    override val key = "Posts"

    override fun get(refresh: Boolean): Single<List<Post>> = when(refresh) {
        true -> api
                .getPosts()
                .flatMap { set(it) }
        false -> cache.load(key).onErrorResumeNext { get(true) }
    }

    override fun get(postId: String, refresh: Boolean): Single<Post> = when(refresh) {
        true -> api
                .getPost(postId)
                .flatMap { set(it) }
        false -> cache
                .load(key)
                .map { it.first { it.id == postId } }
                .onErrorResumeNext { get(postId, true) }
    }

    private fun set(post: Post) = cache
            .load(key)
            .map { it.filter { it.id != post.id }.plus(post) }
            .flatMap { set(it) }
            .map { post }

    private fun set(posts: List<Post>) = cache.save(key, posts)
}