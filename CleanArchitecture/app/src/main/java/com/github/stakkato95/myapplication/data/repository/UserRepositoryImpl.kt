package com.github.stakkato95.myapplication.data.repository

import com.github.stakkato95.myapplication.data.cache.Cache
import com.github.stakkato95.myapplication.data.model.User
import com.github.stakkato95.myapplication.data.remote.UsersApi
import com.github.stakkato95.myapplication.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: UsersApi,
                                             private val cache: Cache<List<User>>) : UserRepository {


    override val key = "Users"

    override fun get(refresh: Boolean): Single<List<User>> = when(refresh) {
        true -> api
                .getUsers()
                .flatMap { set(it) }
        false -> cache.load(key).onErrorResumeNext { get(true) }
    }

    override fun get(userId: String, refresh: Boolean): Single<User> = when(refresh) {
        true -> api
                .getUser(userId)
                .flatMap { set(it) }
                .map { it }
        false -> cache
                .load(key)
                .map { it.first { it.id == userId } }
                .onErrorResumeNext { get(userId, true) }
    }

    private fun set(users: List<User>) = cache.save(key, users)

    private fun set(user: User) = cache
            .load(key)
            .map { it.filter { it.id != user.id }.plus(user) }
            .flatMap { set(it) }
            .map { user }
}