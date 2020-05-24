package com.example.whatsnewinandroid.data.repository

import com.example.whatsnewinandroid.data.responses.BaseResponse
import com.example.whatsnewinandroid.data.source.remote.AwsRemoteDataSource
import com.example.whatsnewinandroid.extentions.toMD5

// todo [later] need to pick them from [local.properties] file
private const val API_PUBLIC_KEY = "daab098c007865963e16cbb4f9cef8aa"
private const val API_PRIVATE_KEY = "20b299205a9c6bca48daf7bfd3bed4b8a818e66b"
private const val HASH_FORMAT = "%s%s%s"

class AwsRepository(private val service: AwsRemoteDataSource) {

    suspend fun getTestData(offset: Int = 0, limit: Int = 0): BaseResponse<String> {
        val timestamp = System.currentTimeMillis().toString()
        return service.getTestData(
            apiKey = API_PUBLIC_KEY,
            hash = generateApiHash(timestamp),
            timestamp = timestamp,
            offset = offset,
            limit = limit
        )
    }

    // ============================================================================================
    //  Private generators methods
    // ============================================================================================

    /**
     * Generate a md5 digest of the timestamp parameter, private API key and public.
     *
     * @param timestamp A digital current record of the time.
     * @return The MD5 Hash
     */
    private fun generateApiHash(timestamp: String) =
        HASH_FORMAT.format(timestamp, API_PRIVATE_KEY, API_PUBLIC_KEY).toMD5()

}