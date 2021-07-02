package com.rakuten.myapplication.data

import com.google.gson.annotations.SerializedName
import java.util.*

data class BitBucketRepo(
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