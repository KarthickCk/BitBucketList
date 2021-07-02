package com.rakuten.myapplication

import android.app.Application
import com.rakuten.myapplication.di.networkModule
import org.koin.core.context.startKoin

class BitBucketApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(networkModule)
        }
    }
}