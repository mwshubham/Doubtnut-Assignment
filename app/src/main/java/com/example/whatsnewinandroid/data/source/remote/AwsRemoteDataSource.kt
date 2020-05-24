package com.example.whatsnewinandroid.data.source.remote

import com.example.whatsnewinandroid.data.responses.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AwsRemoteDataSource {

    @GET("TestLambda")
    suspend fun getTestData(
        @Query("apiKey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") timestamp: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): BaseResponse<String>
}