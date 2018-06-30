package com.github.stakkato95.myapplication.presentation.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.stakkato95.myapplication.domain.usecase.PostsUseCase
import com.github.stakkato95.myapplication.presentation.model.PostItem
import com.github.stakkato95.myapplication.presentation.model.PostItemMapper
import javax.inject.Inject

class PostsViewModel @Inject constructor(private val useCase: PostsUseCase,
                                         private val mapper: PostItemMapper) : ViewModel() {

    val posts = MutableLiveData<List<PostItem>>()
}