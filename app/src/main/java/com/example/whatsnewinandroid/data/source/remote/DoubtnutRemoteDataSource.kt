package com.example.whatsnewinandroid.data.source.remote

import com.example.whatsnewinandroid.data.responses.DoubtnutBaseResponse
import com.example.whatsnewinandroid.testing.OpenForTesting
import retrofit2.http.GET
import retrofit2.http.Query

@OpenForTesting
interface DoubtnutRemoteDataSource {
    /**
     * Fetches lists of address based on the [queryString] passed by the user.
     *
     * https://digi-api.aws.in/compassLocation/rest/address/autocomplete?queryString=aws&city=gurgaon
     * @param city address associated to city
     * @param queryString address text to be searched
     */
    @GET("compassLocation/rest/address/autocomplete")
    suspend fun getAddressList(
        @Query("queryString") queryString: String,
        @Query("city") city: String = "gurgaon"
    ): DoubtnutBaseResponse
}