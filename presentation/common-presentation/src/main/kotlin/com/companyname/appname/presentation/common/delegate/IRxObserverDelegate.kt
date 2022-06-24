package com.companyname.appname.presentation.common.delegate

import androidx.lifecycle.LifecycleOwner
import io.reactivex.Flowable

interface IRxObserverDelegate {
    fun registerObserverDelegate(owner: LifecycleOwner)
    fun <T> Flowable<T>.observe(observer: (T) -> Unit)
}