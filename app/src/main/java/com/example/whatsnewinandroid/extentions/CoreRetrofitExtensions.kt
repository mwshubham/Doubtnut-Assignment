package com.example.whatsnewinandroid.extentions

import retrofit2.Retrofit

/**
 * Synthetic sugaring to create Retrofit Service.
 */
inline fun <reified T> Retrofit.create(): T = create(T::class.java)