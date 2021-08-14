package com.rakuten.myapplication.domain

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
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
        @SerializedName("website")
        val website: String?,
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

    companion object {
        fun getUsers(): List<Repo> {
            val links = Repo.Links(Repo.Links.Href("https://bytebucket.org/ravatar/%7B3f630668-75f1-4903-ae5e-8ea37437e09e%7D?ts=java"))
            val date = SimpleDateFormat("dd-MM-yyyy").parse("14-02-2018")
            val repo = Repo("Repo 1", "www.google.com", "Type", links, date)
            return listOf(repo)
        }
    }
}