package com.rakuten.myapplication.data

import com.rakuten.myapplication.Utils.singleSchedulers
import com.rakuten.myapplication.domain.BitBucketApi
import com.rakuten.myapplication.domain.BitBucketListItem
import com.rakuten.myapplication.domain.IListItem
import io.reactivex.Single

class BitBucketRepository(private val bitBucketApi: BitBucketApi) {

    fun getRepoList(nextPageURL: String?): Single<Pair<List<IListItem>, String?>> {

        val api = when (nextPageURL == null) {
            true -> bitBucketApi.getRepos()
            else -> bitBucketApi.getNextPageRepos(nextPageURL)
        }

        return api
            .compose(singleSchedulers())
            .map {
                val list = it.repos.map { item ->
                    BitBucketListItem(item)
                }.toMutableList<IListItem>()
                return@map Pair(list, it.nextPage)
            }
    }
}