package com.example.whatsnewinandroid.di.modules.fragments

import androidx.lifecycle.ViewModel
import com.example.whatsnewinandroid.di.scope.ViewModelKey
import com.example.whatsnewinandroid.ui.fragments.newsdetails.NewsDetailsFragmentVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class NewsDetailsModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsDetailsFragmentVM::class)
    internal abstract fun bindNewsDetailsFragmentVM(viewModel: NewsDetailsFragmentVM): ViewModel

}