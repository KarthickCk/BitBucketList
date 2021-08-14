package com.rakuten.myapplication.ui

import com.rakuten.myapplication.BaseTest
import com.rakuten.myapplication.domain.BitBucketRepo
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class BitBucketViewModelTest: BaseTest() {

    @Mock
    lateinit var bitBucketRepository: BitBucketRepository

    private lateinit var bitBucketViewModel: BitBucketViewModel

    @Before
    fun setUp() {
        bitBucketViewModel = BitBucketViewModel(bitBucketRepository)
    }

    @Test
    fun testGetRepos() {

        val single = Single.just(Pair(emptyList<BitBucketRepo.Repo>(), ""))
        Mockito.doReturn(single)
            .`when`(bitBucketRepository)
            .getRepoList()

        bitBucketViewModel.getRepos()

        Assert.assertEquals(bitBucketViewModel.nextPageURL, "")
    }
}