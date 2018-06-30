package com.github.stakkato95.myapplication.presentation.di

import com.github.stakkato95.myapplication.data.remote.PostsApi
import com.github.stakkato95.myapplication.data.remote.UsersApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    private val BASE_URL = "http://jsonplaceholder.typicode.com/"
    private val TIMEOUT = 10L

    @Provides
    @Singleton
    fun provieOkHtttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()

        interceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(interceptor)

        clientBuilder.connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        clientBuilder.readTimeout(TIMEOUT, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(TIMEOUT, TimeUnit.SECONDS)

        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providePostsApi(retrofit: Retrofit) = retrofit.create(PostsApi::class.java)

    @Provides
    @Singleton
    fun provideUsersApi(retrofit: Retrofit) = retrofit.create(UsersApi::class.java)
}