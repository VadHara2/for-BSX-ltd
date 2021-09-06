package com.vadhara7.posts.di

import android.app.Application
import androidx.room.Room
import com.vadhara7.posts.api.PostApi
import com.vadhara7.posts.data.PostDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(PostApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRestaurantApi(retrofit: Retrofit): PostApi =
        retrofit.create(PostApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : PostDatabase =
        Room.databaseBuilder(app, PostDatabase::class.java, "post_database")
            .fallbackToDestructiveMigration()
            .build()
}