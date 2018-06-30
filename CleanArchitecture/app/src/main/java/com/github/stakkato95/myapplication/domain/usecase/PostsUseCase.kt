package com.github.stakkato95.myapplication.domain.usecase

import com.github.stakkato95.myapplication.data.model.Post
import com.github.stakkato95.myapplication.data.model.User
import com.github.stakkato95.myapplication.domain.repository.PostRepository
import com.github.stakkato95.myapplication.domain.repository.UserRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class PostsUseCase @Inject constructor(private val userRepository: UserRepository,
                                       private val postRepository: PostRepository,
                                       private val mapper: CombinedUserPostMapper) {

    fun get(refresh: Boolean): Single<List<CombinedUserPost>> = Single.zip(userRepository.get(refresh), postRepository.get(refresh), BiFunction { users, posts -> mapper.map(users, posts) })
}

data class CombinedUserPost(val user: User, val post: Post)

class CombinedUserPostMapper @Inject constructor() {

    fun map(users: List<User>, posts: List<Post>) = posts.map { post -> CombinedUserPost(users.first { it.id == post.id }, post) }
}