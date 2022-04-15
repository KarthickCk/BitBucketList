package com.rakuten.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rakuten.myapplication.data.BitBucketRepositor
import com.rakuten.myapplication.domain.BitBucketRepo

class BitBucketsViewModel(val bitBucketRepositor: BitBucketRepositor): ViewModel() {
    val repositoryList: MutableLiveData<List<BitBucketRepo.Repo>> = MutableLiveData()

    fun getRepoList() {
        bitBucketRepositor.getRepoList { list, exception ->
            if (exception == null) {
                repositoryList.postValue(list)
            }
        }
    }

    class MyBitBucketViewModel(val bitBucketRepositor: BitBucketRepositor): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return BitBucketsViewModel(bitBucketRepositor) as T
        }
    }
}