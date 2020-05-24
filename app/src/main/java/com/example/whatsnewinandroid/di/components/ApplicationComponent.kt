package com.example.whatsnewinandroid.di.components

import android.content.Context
import com.example.whatsnewinandroid.MainApplication
import com.example.whatsnewinandroid.di.modules.ActivityBuilderModule
import com.example.whatsnewinandroid.di.modules.CoreModule
import com.example.whatsnewinandroid.di.modules.DatabaseModule
import com.example.whatsnewinandroid.di.modules.NewsModule
import com.example.whatsnewinandroid.di.modules.network.AwsNetworkModule
import com.example.whatsnewinandroid.di.modules.network.DoubtnutNetworkModule
import com.example.whatsnewinandroid.di.modules.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CoreModule::class,
        AndroidInjectionModule::class,
        ActivityBuilderModule::class,
        DatabaseModule::class,

        NetworkModule::class,
        AwsNetworkModule::class,
        DoubtnutNetworkModule::class,
        NewsModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<MainApplication> {
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance appContext: Context): ApplicationComponent
    }
}