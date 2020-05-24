package com.example.whatsnewinandroid.di.modules

import com.example.whatsnewinandroid.di.scope.ActivityScope
import com.example.whatsnewinandroid.ui.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class
        ]
    )
    abstract fun bindMainActivity(): MainActivity

}