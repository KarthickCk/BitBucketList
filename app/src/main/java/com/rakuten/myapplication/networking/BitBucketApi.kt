package com.rakuten.myapplication.networking

import com.rakuten.myapplication.domain.BitBucketRepo
import retrofit2.Call
import retrofit2.http.GET

interface BitBucketApi {

    @GET("2.0/repositories")
    fun getRepositories(): Call<BitBucketRepo>
}