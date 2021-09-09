package com.vadhara7.posts.di

import android.app.Application
import androidx.room.Room
import com.vadhara7.posts.api.PostService
import com.vadhara7.posts.db.PostDatabase
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
            .baseUrl(PostService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providePostService(retrofit: Retrofit): PostService =
        retrofit.create(PostService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): PostDatabase =
        Room.databaseBuilder(app, PostDatabase::class.java, "post_database")
            .fallbackToDestructiveMigration()
            .build()
}