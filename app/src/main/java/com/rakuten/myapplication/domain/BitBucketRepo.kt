package com.rakuten.myapplication.domain

import com.google.gson.annotations.SerializedName
import java.util.*

data class BitBucketRepo(
    @SerializedName("values")
    val repos: List<Repo>,
    @SerializedName("next")
    val nextPage: String?
) {
    data class Repo(
        @SerializedName("name")
        val name: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("links")
        val links: Links,
        @SerializedName("created_on")
        val date: Date
    ) {
        data class Links(
            @SerializedName("avatar")
            val href: Href
        ) {
            data class Href(
                @SerializedName("href")
                val avatar: String
            )
        }
    }
}