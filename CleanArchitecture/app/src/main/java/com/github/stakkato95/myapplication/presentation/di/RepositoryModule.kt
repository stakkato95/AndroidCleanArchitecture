package com.github.stakkato95.myapplication.presentation.di

import com.github.stakkato95.myapplication.data.repository.PostRepositoryImpl
import com.github.stakkato95.myapplication.data.repository.UserRepositoryImpl
import com.github.stakkato95.myapplication.domain.repository.PostRepository
import com.github.stakkato95.myapplication.domain.repository.UserRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindPostRepository(repository: PostRepositoryImpl) : PostRepository

    @Binds
    abstract fun bindUserRepository(repository: UserRepositoryImpl) : UserRepository
}