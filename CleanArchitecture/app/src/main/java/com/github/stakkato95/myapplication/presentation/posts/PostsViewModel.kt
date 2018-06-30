package com.github.stakkato95.myapplication.presentation.posts

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.github.stakkato95.myapplication.domain.usecase.PostsUseCase
import com.github.stakkato95.myapplication.presentation.Data
import com.github.stakkato95.myapplication.presentation.DataState
import com.github.stakkato95.myapplication.presentation.model.PostItem
import com.github.stakkato95.myapplication.presentation.model.PostItemMapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Inject

class PostsViewModel @Inject constructor(private val useCase: PostsUseCase,
                                         private val mapper: PostItemMapper) : ViewModel() {

    val posts = MutableLiveData<Data<List<PostItem>>>()
    private val compositeDisposable = CompositeDisposable()


    init {
        loadData(true)
    }

    fun loadData(refresh: Boolean = false) {
        compositeDisposable.add(
                useCase
                        .get(refresh)
                        .doOnSubscribe { posts.postValue(Data(DataState.LOADING)) }
                        .map { mapper.mapToPresentation(it) }
                        .subscribeOn(io())
                        .observeOn(io())
                        .subscribe({
                            posts.postValue(Data(DataState.SUCCESS, it))
                        }, {
                            posts.postValue(Data(DataState.ERROR, null, it.message))
                        })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}