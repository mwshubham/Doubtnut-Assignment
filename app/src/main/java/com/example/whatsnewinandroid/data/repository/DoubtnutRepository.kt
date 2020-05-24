package com.example.whatsnewinandroid.data.repository

import com.example.whatsnewinandroid.data.responses.DoubtnutBaseResponse
import com.example.whatsnewinandroid.data.source.remote.DoubtnutRemoteDataSource
import com.example.whatsnewinandroid.testing.OpenForTesting
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OpenForTesting
open class DoubtnutRepository
@Inject constructor(
    private val doubtnutRemoteDataSource: DoubtnutRemoteDataSource
) {

    suspend fun getAddressList(queryString: String): DoubtnutBaseResponse {
        return doubtnutRemoteDataSource.getAddressList(queryString = queryString)
    }

}