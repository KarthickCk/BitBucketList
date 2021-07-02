package com.rakuten.myapplication.domain

import com.rakuten.myapplication.data.BitBucketRepo
import retrofit2.http.GET

interface BitBucketApi {
    @GET("/repositories")
    fun getRepos(): List<BitBucketRepo>
}