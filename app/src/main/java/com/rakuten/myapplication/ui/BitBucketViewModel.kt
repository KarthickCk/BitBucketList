package com.rakuten.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rakuten.myapplication.data.BitBucketRepository
import com.rakuten.myapplication.domain.BitBucketRepo
import io.reactivex.disposables.CompositeDisposable

class BitBucketViewModel(
    private val bitBucketRepository: BitBucketRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val listItem = MutableLiveData<UIState>()
    private var nextPageURL: String? = null

    fun getRepos() {
        compositeDisposable.add(
            bitBucketRepository.getRepoList(nextPageURL)
                .doOnSubscribe {
                    listItem.value = UIState.Loading(true)
                }
                .subscribe({
                    listItem.value = UIState.Loading(false)
                    listItem.value = UIState.BitBucketList(it.first)
                    this.nextPageURL = it.second
                    listItem.value = UIState.HideNextButton(it.second == null)
                }, {
                    listItem.value = UIState.Loading(false)
                    listItem.value = UIState.Error(it.localizedMessage)
                })
        )
    }

    sealed class UIState {
        class BitBucketList(val list: List<BitBucketRepo.Repo>) : UIState()
        class Error(val message: String) : UIState()
        class Loading(val isLoading: Boolean) : UIState()
        class HideNextButton(val hide: Boolean) : UIState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}