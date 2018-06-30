package com.github.stakkato95.myapplication.presentation.di

import com.github.stakkato95.myapplication.PostsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, RepositoryModule::class, ViewModelModule::class])
interface Injector {

    fun inject(activity: PostsActivity)
}