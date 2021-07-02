package com.rakuten.myapplication.data

import com.rakuten.myapplication.Utils.singleSchedulers
import com.rakuten.myapplication.domain.BitBucketApi
import com.rakuten.myapplication.domain.BitBucketRepo
import io.reactivex.Single

class BitBucketRepository(private val bitBucketApi: BitBucketApi) {

    fun getRepoList(nextPageURL: String? = null): Single<Pair<List<BitBucketRepo.Repo>, String?>> {

        val api = when (nextPageURL == null) {
            true -> bitBucketApi.getRepos()
            else -> bitBucketApi.getNextPageRepos(nextPageURL)
        }

        return api
            .compose(singleSchedulers())
            .map {
                return@map Pair(it.repos, it.nextPage)
            }
    }
}