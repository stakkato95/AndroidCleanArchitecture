package com.github.stakkato95.myapplication.data.cache

import com.pacoworks.rxpaper2.RxPaperBook
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Inject

class Cache<T> @Inject constructor() {

    fun load(key: String): Single<T> = RxPaperBook.with(io()).read(key)

    fun save(key: String, obj: T): Single<T> = RxPaperBook.with(io()).write(key, obj).toSingleDefault(obj)
}