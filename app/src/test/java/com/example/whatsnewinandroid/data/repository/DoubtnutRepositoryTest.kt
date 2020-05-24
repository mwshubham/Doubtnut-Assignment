package com.example.whatsnewinandroid.data.repository

import com.example.whatsnewinandroid.data.responses.Address
import com.example.whatsnewinandroid.data.responses.DoubtnutBaseResponse
import com.example.whatsnewinandroid.data.responses.DoubtnutDataResponse
import com.example.whatsnewinandroid.data.source.remote.DoubtnutRemoteDataSource
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit

class DoubtnutRepositoryTest {

    @get:Rule
    var mockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var doubtnutRemoteDataSource: DoubtnutRemoteDataSource

    @Test
    fun getAddressList_mockValidOutput() {
        runBlocking {
            val mockOutput = DoubtnutBaseResponse(
                requestId = "10161706766605102861590206449991",
                data = DoubtnutDataResponse(
                    autoCompleteRequestString = "Android",
                    focusWord = "",
                    addressList = listOf(
                        Address(
                            "MMI_ADDRESS_4GB7QW",
                            "No 3B, Kadugodi Industrial Area, Sadaramangala, Whitefield, Bengaluru, Karnataka, 560067"
                        )
                    )
                )
            )
            `when`(doubtnutRemoteDataSource.getAddressList("Android")).thenReturn(mockOutput)
            Truth.assertThat(doubtnutRemoteDataSource.getAddressList("Android").requestId)
                .isEqualTo("10161706766605102861590206449991")
        }

    }
}