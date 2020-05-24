package com.example.whatsnewinandroid.di.modules.network

import com.example.whatsnewinandroid.data.repository.AwsRepository
import com.example.whatsnewinandroid.data.source.remote.AwsRemoteDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class AwsNetworkModule {
    /**
     * Create a provider method binding for [AwsRemoteDataSource].
     *
     * @return Instance of Aws Remote DAta Source
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): AwsRemoteDataSource = retrofit
        .create(AwsRemoteDataSource::class.java)

    /**
     * Create a provider method binding for [AwsRepository].
     *
     * @return Instance of Aws Repository.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideApiRepository(service: AwsRemoteDataSource) = AwsRepository(service)
}