package com.companyname.appname.presentation.common.delegate

import androidx.lifecycle.LifecycleOwner
import io.reactivex.Observable

interface IRxObserverDelegate {
    fun registerObserverDelegate(owner: LifecycleOwner)
    fun <T> Observable<T>.observe(observer: (T) -> Unit)
}