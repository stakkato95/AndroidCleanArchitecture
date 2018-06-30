package com.github.stakkato95.myapplication.presentation

import android.app.Application
import com.github.stakkato95.myapplication.presentation.di.DaggerInjector
import com.github.stakkato95.myapplication.presentation.di.Injector
import com.pacoworks.rxpaper2.RxPaperBook
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber

class App : Application() {

    lateinit var injector: Injector
        private set

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initTimber()
        initRxJavaErrorHandlingPlugin()
        initRxPaper()
    }

    private fun initDagger() {
        injector = DaggerInjector
                .builder()
                .build()
    }

    fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initRxPaper() = RxPaperBook.init(this)

    private fun initRxJavaErrorHandlingPlugin() = RxJavaPlugins.setErrorHandler { Timber.e(it) }
}