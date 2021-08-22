package com.ugurinci.yemeksepetifinal.di

import com.ugurinci.yemeksepetifinal.data.api.MockAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://611beea222020a00175a47f2.mockapi.io"

    @Singleton
    private val client = OkHttpClient().newBuilder().build()

    @Singleton
    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(client)
        .addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    @Singleton
    fun provideService(): MockAPIService = retrofit.create(MockAPIService::class.java)
}