package com.example.whatsnewinandroid.data.responses

/**
 * News source model describing the source details
 * and the articles under it.
 */
data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsArticle>
)