package com.rakuten.myapplication.data

import android.util.Log
import com.google.gson.Gson
import com.rakuten.myapplication.domain.BitBucketRepo
import com.rakuten.myapplication.networking.BitBucketApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*
import java.lang.Exception
import java.net.URL
import java.util.concurrent.ExecutorService
import javax.net.ssl.HttpsURLConnection

class BitBucketRepositor(
    private val executorService: ExecutorService,
    private val bitBucketApi: BitBucketApi
) {

    fun getRepoList(
        callback: (List<BitBucketRepo.Repo>, Throwable?) -> Unit
    ) {
        executorService.execute {
            bitBucketApi.getRepositories().enqueue(object: Callback<BitBucketRepo> {
                override fun onResponse(
                    call: Call<BitBucketRepo>,
                    response: Response<BitBucketRepo>
                ) {
                    if (response.isSuccessful) {
                        callback.invoke(response.body()?.repos!!, null)
                    }
                }

                override fun onFailure(call: Call<BitBucketRepo>, t: Throwable) {
                    callback.invoke(emptyList(), t)
                }

            })
        }
    }
}