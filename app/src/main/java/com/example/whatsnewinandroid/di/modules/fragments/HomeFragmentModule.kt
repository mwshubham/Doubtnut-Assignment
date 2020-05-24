package com.example.whatsnewinandroid.di.modules.fragments

import androidx.lifecycle.ViewModel
import com.example.whatsnewinandroid.di.scope.ViewModelKey
import com.example.whatsnewinandroid.ui.fragments.home.HomeFragmentVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeFragmentVM::class)
    internal abstract fun bindHomeFragmentVM(viewModel: HomeFragmentVM): ViewModel

}