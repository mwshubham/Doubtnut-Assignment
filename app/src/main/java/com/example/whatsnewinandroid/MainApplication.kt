@file:Suppress("unused")

package com.example.whatsnewinandroid

import android.app.Application
import android.content.Context
import com.example.whatsnewinandroid.constants.CoreLoggingConstants
import com.example.whatsnewinandroid.di.components.DaggerApplicationComponent
import com.example.whatsnewinandroid.utilities.CoreActivityLifecycleCallbacks
import com.example.whatsnewinandroid.utilities.CoreDebugTree
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        initAppDependencyInjection()
        initTimber()
        initFirebaseRemoteConfig()
        registerActivityLifecycleCallbacks()
        Timber.i(CoreLoggingConstants.LOGGING_PLACEHOLDER)
    }

    // ============================================================================================
    //  Private init methods
    // ============================================================================================

    /**
     * Initialize app dependency injection component.
     */
    private fun initAppDependencyInjection() {
        DaggerApplicationComponent
            .factory()
            .create(this)
            .inject(this)
    }

    private fun initFirebaseRemoteConfig() {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = TimeUnit.HOURS.toSeconds(1)
        }
        Firebase.remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        Firebase.remoteConfig.setConfigSettingsAsync(configSettings)
        Firebase.remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    Timber.d("Config params updated: $updated")
                } else {
                    Timber.e("Fetch failed")
                }
            }
    }

    /**
     * Initialize log library Timber only on debug build.
     */
    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(CoreDebugTree())
        }
    }

    private fun registerActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(CoreActivityLifecycleCallbacks())
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    companion object {
        lateinit var appContext: Context
    }

}