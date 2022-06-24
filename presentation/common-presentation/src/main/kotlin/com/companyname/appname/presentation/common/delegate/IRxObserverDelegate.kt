package com.companyname.appname.presentation.common.delegate

import androidx.lifecycle.LifecycleOwner
import io.reactivex.rxjava3.core.Observable

interface IRxObserverDelegate {
    fun registerObserverDelegate(owner: LifecycleOwner)
    fun <T : Any> Observable<T>.observe(observer: (T) -> Unit)
}
