package com.rakuten.myapplication.domain

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface BitBucketApi {
    @GET("/2.0/repositories")
    fun getRepos(): Single<BitBucketRepo>

    @GET
    fun getNextPageRepos(@Url url: String?): Single<BitBucketRepo>
}