package com.example.whatsnewinandroid.di.modules.fragments

import androidx.lifecycle.ViewModel
import com.example.whatsnewinandroid.di.scope.ViewModelKey
import com.example.whatsnewinandroid.ui.fragments.more.MoreFragmentVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MoreFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(MoreFragmentVM::class)
    internal abstract fun bindMoreFragmentVM(viewModel: MoreFragmentVM): ViewModel

}