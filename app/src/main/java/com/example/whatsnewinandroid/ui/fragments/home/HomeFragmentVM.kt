@file:Suppress("unused")

package com.example.whatsnewinandroid.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.whatsnewinandroid.data.repository.NewsRepository
import com.example.whatsnewinandroid.data.responses.NewsArticle
import com.example.whatsnewinandroid.ui.states.ViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
class HomeFragmentVM
@Inject constructor(
    @Suppress("MemberVisibilityCanBePrivate")
    val newsRepository: NewsRepository
) : ViewModel() {

    init {
        Timber.i("init{}")
    }

    val newsArticles: LiveData<ViewState<List<NewsArticle>>> =
        newsRepository.getNewsArticles().asLiveData()

    override fun onCleared() {
        super.onCleared()
        Timber.i("onCleared()")
    }
}
