package com.example.whatsnewinandroid.data.source.remote

import com.example.whatsnewinandroid.BuildConfig
import com.example.whatsnewinandroid.data.responses.NewsResponse
import com.example.whatsnewinandroid.testing.OpenForTesting
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

@OpenForTesting
interface NewsRemoteDataSource {
    /**
     * @param country The 2-letter ISO 3166-1 code of the country you want to get headlines for.
     * @param apiKey Your API key. Alternatively you can provide this via the X-Api-Key HTTP header.
     *
     * Retrieves top headlines / latest news article using News API.
     */
    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "in",
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY
    ): Response<NewsResponse>
}