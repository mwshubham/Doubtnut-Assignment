package com.example.whatsnewinandroid.data.repository

import android.content.Context
import com.example.whatsnewinandroid.data.NetworkBoundResource
import com.example.whatsnewinandroid.data.responses.NewsArticle
import com.example.whatsnewinandroid.data.responses.NewsResponse
import com.example.whatsnewinandroid.data.source.local.NewsArticlesDao
import com.example.whatsnewinandroid.data.source.remote.NewsRemoteDataSource
import com.example.whatsnewinandroid.ui.states.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

@ExperimentalCoroutinesApi
class NewsRepository @Inject constructor(
    private val appContext: Context,
    private val newsArticlesDao: NewsArticlesDao,
    private val newsRemoteDataSource: NewsRemoteDataSource
) {

    fun getNewsArticle(publishedAt: String) = newsArticlesDao.getNewsArticle(publishedAt)

    fun getNewsArticles(): Flow<ViewState<List<NewsArticle>>> {
        return object : NetworkBoundResource<List<NewsArticle>, NewsResponse>() {
            override suspend fun saveNetworkResult(response: NewsResponse) {
                newsArticlesDao.insertArticles(response.articles)
            }

            override fun shouldFetch(data: List<NewsArticle>?): Boolean = true
            override fun loadFromDb(): Flow<List<NewsArticle>> {
                return newsArticlesDao.getNewsArticles()
            }

            override suspend fun fetchFromNetwork(): Response<NewsResponse> {
                return newsRemoteDataSource.getTopHeadlines()
            }
        }
            .asFlow(appContext)
            .flowOn(Dispatchers.IO)
    }
}
