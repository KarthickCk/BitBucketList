package com.rakuten.myapplication.data

import com.rakuten.myapplication.domain.BitBucketApi
import com.rakuten.myapplication.domain.BitBucketListItem
import com.rakuten.myapplication.domain.IListItem
import com.rakuten.myapplication.domain.NextPageItem
import io.reactivex.Single

class BitBucketRepository(private val bitBucketApi: BitBucketApi) {

    fun getRepoList(nextPageURL: String?): Single<List<IListItem>> {

        val api = when (nextPageURL != null) {
            true -> bitBucketApi.getRepos()
            else -> bitBucketApi.getRepos()
        }

        return api.map {
            val list = it.repos.map { item ->
                BitBucketListItem(item)
            }.toMutableList<IListItem>()
            if (it.nextPage != null)
                list.add(NextPageItem(it.nextPage))
            return@map list
        }
    }
}