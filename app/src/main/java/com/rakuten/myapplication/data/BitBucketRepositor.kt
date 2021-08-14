package com.rakuten.myapplication.data

import android.util.Log
import com.google.gson.Gson
import com.rakuten.myapplication.domain.BitBucketRepo
import java.io.*
import java.lang.Exception
import java.net.URL
import java.util.concurrent.ExecutorService
import javax.net.ssl.HttpsURLConnection

class BitBucketRepositor(
    private val executorService: ExecutorService,
    private val gson: Gson
) {

    fun getRepoList(
        callback: (List<BitBucketRepo.Repo>) -> Unit
    ) {
        executorService.execute {
            val api = "https://api.bitbucket.org/2.0/repositories"
            val url = URL(api)
            var byteArrayInputStream: BufferedReader? = null
            (url.openConnection() as HttpsURLConnection).run {
                try {
                    requestMethod = "GET"
                    val byteArrayInputStream = BufferedReader(InputStreamReader(inputStream))
                    val builder = StringBuilder()
                    var line: String?
                    while (byteArrayInputStream.readLine().also { line = it } != null) {
                        builder.append(line)
                    }
                    val bitBucketRepo = gson.fromJson(builder.toString(), BitBucketRepo::class.java)
                    callback.invoke(bitBucketRepo.repos)
                } catch (exception: Exception) {
                    Log.e("------>", "---->" + exception)
                } finally {
                    byteArrayInputStream?.close()
                    disconnect()
                }
            }
        }
    }
}