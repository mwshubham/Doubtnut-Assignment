package com.example.whatsnewinandroid.di.modules

import androidx.lifecycle.ViewModel
import com.example.whatsnewinandroid.di.modules.fragments.*
import com.example.whatsnewinandroid.di.scope.ViewModelKey
import com.example.whatsnewinandroid.ui.activities.MainActivityVM
import com.example.whatsnewinandroid.ui.fragments.home.HomeFragment
import com.example.whatsnewinandroid.ui.fragments.listen.ListenFragment
import com.example.whatsnewinandroid.ui.fragments.more.MoreFragment
import com.example.whatsnewinandroid.ui.fragments.newsdetails.NewsDetailsFragment
import com.example.whatsnewinandroid.ui.fragments.read.ReadFragment
import com.example.whatsnewinandroid.ui.fragments.search.SearchFragment
import com.example.whatsnewinandroid.ui.fragments.watch.WatchFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityVM::class)
    internal abstract fun bindMainActivityVM(viewModel: MainActivityVM): ViewModel

    @ExperimentalCoroutinesApi
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [NewsDetailsModule::class])
    abstract fun bindNewsDetailsFragment(): NewsDetailsFragment

    @ContributesAndroidInjector(modules = [ReadFragmentModule::class])
    abstract fun bindReadFragment(): ReadFragment

    @ContributesAndroidInjector(modules = [WatchFragmentModule::class])
    abstract fun bindWatchFragment(): WatchFragment

    @ContributesAndroidInjector(modules = [ListenFragmentModule::class])
    abstract fun bindListenFragment(): ListenFragment

    @ContributesAndroidInjector(modules = [MoreFragmentModule::class])
    abstract fun bindMoreFragment(): MoreFragment

    @ContributesAndroidInjector(modules = [SearchFragmentModule::class])
    abstract fun bindSearchFragment(): SearchFragment

}