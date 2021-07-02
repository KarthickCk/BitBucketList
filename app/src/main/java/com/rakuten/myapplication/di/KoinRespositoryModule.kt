package com.rakuten.myapplication.di

import com.rakuten.myapplication.data.BitBucketRepository
import org.koin.dsl.module

val repositoryModules = module {
    single {
        BitBucketRepository(bitBucketApi = get())
    }
}