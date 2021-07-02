package com.rakuten.myapplication

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.functions.Functions
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import okio.Okio
import org.mockito.MockitoAnnotations
import java.nio.file.Paths

open class BaseTest {

    init {
        RxJavaPlugins.setIoSchedulerHandler {
            Schedulers.trampoline()
        }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            Schedulers.trampoline()
        }
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setErrorHandler(Functions.emptyConsumer())
        MockitoAnnotations.initMocks(this)
    }
}
