package com.github.stakkato95.myapplication.presentation.posts

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.stakkato95.myapplication.R
import com.github.stakkato95.myapplication.presentation.Data
import com.github.stakkato95.myapplication.presentation.DataState
import com.github.stakkato95.myapplication.presentation.appInjector
import com.github.stakkato95.myapplication.presentation.model.PostItem
import com.github.stakkato95.myapplication.presentation.observe
import com.github.stakkato95.myapplication.presentation.stopRefreshing
import com.github.stakkato95.myapplication.presentation.withViewModel
import kotlinx.android.synthetic.main.activity_posts.*
import javax.inject.Inject

class PostsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        appInjector.inject(this)

        withViewModel<PostsViewModel>(viewModelFactory) {
            swipeRefreshLayout.setOnRefreshListener { loadData(true) }
            observe(posts, ::updatePosts)
        }
    }

    private fun updatePosts(data: Data<List<PostItem>>?) {
        when (data?.dataState) {
            DataState.LOADING -> { }
            DataState.SUCCESS -> swipeRefreshLayout.stopRefreshing()
            DataState.ERROR -> swipeRefreshLayout.stopRefreshing()
        }
    }
}
