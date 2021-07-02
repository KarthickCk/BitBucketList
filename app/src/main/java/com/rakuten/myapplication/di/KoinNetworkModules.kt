package com.rakuten.myapplication.di

import com.google.gson.Gson
import com.rakuten.myapplication.domain.BitBucketApi
import io.reactivex.schedulers.Schedulers
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

val networkModule = module {

    factory<RxJava2CallAdapterFactory> {
        RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    } bind CallAdapter.Factory::class

    factory<GsonConverterFactory> {
        GsonConverterFactory.create()
    } bind Converter.Factory::class

    factory<ScalarsConverterFactory> {
        ScalarsConverterFactory.create()
    } bind Converter.Factory::class

    factory<BitBucketApi> {
        val builder = Retrofit.Builder()
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
        builder.baseUrl("https://api.bitbucket.org").build()
            .create(BitBucketApi::class.java)
    }
}