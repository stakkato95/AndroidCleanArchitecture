package com.github.stakkato95.myapplication.presentation.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.stakkato95.myapplication.presentation.ViewModelFactory
import com.github.stakkato95.myapplication.presentation.ViewModelKey
import com.github.stakkato95.myapplication.presentation.posts.PostsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    internal abstract fun bindPostsViewModel(viewModel: PostsViewModel): ViewModel
}