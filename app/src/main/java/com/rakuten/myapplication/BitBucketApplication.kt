package com.rakuten.myapplication

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class BitBucketApplication: Application() {

    private val executor: ExecutorService = Executors.newFixedThreadPool(4)

    fun getExecutor(): ExecutorService {
        return executor
    }
}