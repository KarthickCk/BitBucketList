package com.rakuten.myapplication.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rakuten.myapplication.data.BitBucketRepository
import com.rakuten.myapplication.domain.IListItem
import io.reactivex.disposables.CompositeDisposable

class BitBucketViewModel(
    private val bitBucketRepository: BitBucketRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val listItem = MutableLiveData<UIState>()

    fun getRepos(nextPageURL: String? = null) {
        compositeDisposable.add(bitBucketRepository.getRepoList(nextPageURL)
            .subscribe({
                listItem.value = UIState.BitBucketList(it)
            }, {
                listItem.value = UIState.Error(it.localizedMessage)
            })
        )
    }

    sealed class UIState {
        class BitBucketList(list: List<IListItem>) : UIState()
        class Error(val message: String) : UIState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}