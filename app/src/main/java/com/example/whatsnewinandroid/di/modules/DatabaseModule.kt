@file:Suppress("KDocUnresolvedReference")

package com.example.whatsnewinandroid.di.modules

import android.content.Context
import com.example.whatsnewinandroid.data.source.local.NewsArticlesDao
import com.example.whatsnewinandroid.data.source.local.NewsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Class that contributes to the object graph [ApplicationComponent].
 *
 * Considering [NewsDatabase] as a main database for now...
 *
 * @see Module
 */
@Module
class DatabaseModule {

    /**
     * Create a provider method binding for Main Database.
     *
     * @return Instance of main database.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideMainDatabase(context: Context) = NewsDatabase.buildDefault(context)

    /**
     * Create a provider method binding for [NewsArticlesDao].
     *
     * @return Instance of news article data access object.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideNewsArticlesDao(newsDatabase: NewsDatabase) =
        newsDatabase.newsArticlesDao()

}