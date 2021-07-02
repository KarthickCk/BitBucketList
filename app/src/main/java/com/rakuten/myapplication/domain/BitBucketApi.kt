package com.rakuten.myapplication.domain

import io.reactivex.Single
import retrofit2.http.GET

interface BitBucketApi {
    @GET("/2.0/repositories")
    fun getRepos(): Single<BitBucketRepo>
}