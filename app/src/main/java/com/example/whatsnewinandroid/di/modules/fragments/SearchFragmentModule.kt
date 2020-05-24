package com.example.whatsnewinandroid.di.modules.fragments

import androidx.lifecycle.ViewModel
import com.example.whatsnewinandroid.di.scope.ViewModelKey
import com.example.whatsnewinandroid.ui.fragments.search.SearchFragmentVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchFragmentVM::class)
    internal abstract fun bindSearchFragmentVM(viewModel: SearchFragmentVM): ViewModel

}