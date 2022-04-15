package com.rakuten.myapplication.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {

    fun getBitBucketApi(): BitBucketApi {
        return getRetrofit().create(BitBucketApi::class.java)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.bitbucket.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}