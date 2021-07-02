package com.rakuten.myapplication

import android.app.Application
import com.rakuten.myapplication.di.networkModule
import com.rakuten.myapplication.di.repositoryModules
import com.rakuten.myapplication.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BitBucketApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BitBucketApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModules,
                    viewModelModules
                )
            )
        }
    }
}