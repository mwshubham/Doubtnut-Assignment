package com.example.whatsnewinandroid.ui.fragments.home

import androidx.navigation.fragment.FragmentNavigator
import com.example.whatsnewinandroid.data.responses.NewsArticle

/**
 * Describes all the events originated from
 * [NewsArticlesAdapter].
 */
sealed class NewsAdapterEvent {
    data class ClickEvent(
        val newsArticle: NewsArticle,
        val extras: FragmentNavigator.Extras
    ) : NewsAdapterEvent()
}