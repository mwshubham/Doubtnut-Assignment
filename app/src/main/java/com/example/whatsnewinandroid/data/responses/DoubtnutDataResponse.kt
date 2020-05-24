package com.example.whatsnewinandroid.data.responses

/**
 * API data network response format.
 *
 * @param autoCompleteRequestString
 * @param focusWord
 * @param addressList The list of [Address] returned by the call.
 */
data class DoubtnutDataResponse(
    val autoCompleteRequestString: String,
    val focusWord: String,
    val addressList: List<Address>
)

/**
 * Address data
 *
 * @param id
 * @param addressString
 */
data class Address(
    val id: String,
    val addressString: String
)
