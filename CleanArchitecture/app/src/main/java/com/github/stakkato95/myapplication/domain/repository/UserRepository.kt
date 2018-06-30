package com.github.stakkato95.myapplication.domain.repository

import com.github.stakkato95.myapplication.data.model.User
import io.reactivex.Single

interface UserRepository {

    val key: String

    fun get(refresh: Boolean): Single<List<User>>

    fun get(userId: String, refresh: Boolean): Single<User>
}