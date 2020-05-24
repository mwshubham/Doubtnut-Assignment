package com.example.whatsnewinandroid.di.modules

import android.content.Context
import com.example.whatsnewinandroid.data.repository.NewsRepository
import com.example.whatsnewinandroid.data.source.local.NewsArticlesDao
import com.example.whatsnewinandroid.data.source.remote.NewsRemoteDataSource
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NewsModule {

    /**
     * Create a provider method binding for [NewsRemoteDataSource].
     *
     * @return Instance of News Remote Data Source.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(retrofit: Retrofit): NewsRemoteDataSource =
        retrofit.create(NewsRemoteDataSource::class.java)

    /**
     * Create a provider method binding for [NewsRepository].
     *
     * @return Instance of Doubtnut Repository.
     * @see Provides
     */
    @ExperimentalCoroutinesApi
    @Singleton
    @Provides
    fun provideNewsRepository(
        appContext: Context,
        newsArticlesDao: NewsArticlesDao,
        newsRemoteDataSource: NewsRemoteDataSource
    ) =
        NewsRepository(
            appContext,
            newsArticlesDao,
            newsRemoteDataSource
        )

}