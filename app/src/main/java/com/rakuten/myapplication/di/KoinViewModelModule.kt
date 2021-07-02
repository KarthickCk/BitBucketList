package com.rakuten.myapplication.di

import com.rakuten.myapplication.ui.BitBucketViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel {
        BitBucketViewModel(bitBucketRepository = get())
    }
}