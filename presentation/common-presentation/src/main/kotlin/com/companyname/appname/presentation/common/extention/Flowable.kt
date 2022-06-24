package com.companyname.appname.presentation.common.extention

import androidx.lifecycle.LifecycleOwner
import com.companyname.appname.presentation.common.RxLifecycleHandler
import io.reactivex.Flowable

fun <T> Flowable<T>.observe(owner: LifecycleOwner, observer: (T) -> Unit) {
    RxLifecycleHandler(owner, this, observer)
}
