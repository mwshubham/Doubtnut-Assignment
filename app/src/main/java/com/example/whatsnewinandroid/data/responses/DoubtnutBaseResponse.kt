package com.example.whatsnewinandroid.data.responses

/**
 * Doubtnut network response.
 *
 * @param requestId
 * @param data The results returned by the call.
 */
data class DoubtnutBaseResponse(
    val requestId: String,
    val data: DoubtnutDataResponse
)