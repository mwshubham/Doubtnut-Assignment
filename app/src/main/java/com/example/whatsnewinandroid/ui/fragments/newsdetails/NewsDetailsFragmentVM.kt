package com.example.whatsnewinandroid.ui.fragments.newsdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.whatsnewinandroid.data.repository.NewsRepository
import com.example.whatsnewinandroid.data.responses.NewsArticle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class NewsDetailsFragmentVM
@ExperimentalCoroutinesApi
@Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    lateinit var publishedAt: String

    @ExperimentalCoroutinesApi
    val newsArticle: LiveData<NewsArticle> by lazy {
        newsRepository.getNewsArticle(publishedAt)
    }

}