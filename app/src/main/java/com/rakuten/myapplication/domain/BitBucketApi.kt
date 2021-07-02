package com.rakuten.myapplication.domain

import io.reactivex.Single
import retrofit2.http.GET

interface BitBucketApi {
    @GET("/repositories")
    fun getRepos(): Single<BitBucketRepo>
}