package com.github.stakkato95.myapplication.presentation.di

import com.github.stakkato95.myapplication.presentation.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides
    @Singleton
    fun provideApp() = app
}