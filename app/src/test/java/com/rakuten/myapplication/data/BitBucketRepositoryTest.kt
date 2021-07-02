package com.rakuten.myapplication.data

import com.rakuten.myapplication.BaseTest
import com.rakuten.myapplication.domain.BitBucketApi
import com.rakuten.myapplication.domain.BitBucketRepo
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import java.util.*

class BitBucketRepositoryTest : BaseTest() {

    @Mock
    lateinit var bitBucketApi: BitBucketApi

    private lateinit var bitBucketRepository: BitBucketRepository

    @Before
    fun setUp() {
        bitBucketRepository = BitBucketRepository(bitBucketApi)
    }

    @Test
    fun getRepoListWithNullURL() {

        val bitbucketRepo = BitBucketRepo(
            listOf(
                BitBucketRepo.Repo(
                    "Name", "", "type",
                    BitBucketRepo.Repo.Links(BitBucketRepo.Repo.Links.Href("")), Date()
                )
            ), ""
        )

        val single = Single.just(bitbucketRepo)
        Mockito.doReturn(single)
            .`when`(bitBucketApi)
            .getRepos()

        bitBucketRepository.getRepoList()
            .test()
            .assertComplete()
            .assertNoErrors()

        Mockito.verify(bitBucketApi).getRepos()
        Mockito.verify(bitBucketApi, Mockito.times(0))
            .getNextPageRepos(Mockito.anyString())
    }

    @Test
    fun getRepoListWithURL() {

        val bitbucketRepo = BitBucketRepo(
            listOf(
                BitBucketRepo.Repo(
                    "Name", "", "type",
                    BitBucketRepo.Repo.Links(BitBucketRepo.Repo.Links.Href("")), Date()
                )
            ), ""
        )

        val single = Single.just(bitbucketRepo)
        Mockito.doReturn(single)
            .`when`(bitBucketApi)
            .getNextPageRepos(Mockito.anyString())

        bitBucketRepository.getRepoList("")

            .test()
            .assertComplete()
            .assertNoErrors()

        Mockito.verify(bitBucketApi, Mockito.times(0)).getRepos()
        Mockito.verify(bitBucketApi).getNextPageRepos(Mockito.anyString())
    }
}