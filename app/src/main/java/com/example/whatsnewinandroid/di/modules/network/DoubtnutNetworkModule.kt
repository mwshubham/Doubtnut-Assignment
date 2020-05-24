package com.example.whatsnewinandroid.di.modules.network

import com.example.whatsnewinandroid.data.repository.DoubtnutRepository
import com.example.whatsnewinandroid.data.source.remote.DoubtnutRemoteDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DoubtnutNetworkModule {

    /**
     * Create a provider method binding for [DoubtnutRemoteDataSource].
     *
     * @return Instance of Doubtnut Remote Data Source.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideDoubtnutRemoteDataSource(retrofit: Retrofit): DoubtnutRemoteDataSource =
        retrofit.create(DoubtnutRemoteDataSource::class.java)

    /**
     * Create a provider method binding for [DoubtnutRepository].
     *
     * @return Instance of Doubtnut Repository.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideDoubtnutRepository(service: DoubtnutRemoteDataSource) =
        DoubtnutRepository(service)

}