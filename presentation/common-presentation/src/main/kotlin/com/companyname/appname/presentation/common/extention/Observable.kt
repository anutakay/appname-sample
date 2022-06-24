package com.companyname.appname.presentation.common.extention

import androidx.lifecycle.LifecycleOwner
import com.companyname.appname.presentation.common.RxLifecycleHandler
import io.reactivex.Observable

fun <T> Observable<T>.observe(owner: LifecycleOwner, observer: (T) -> Unit) {
    RxLifecycleHandler(owner, this, observer)
}

inline fun <T, reified E : T> Observable<in T>.filterTo(
    @Suppress("UNUSED_PARAMETER") target: Class<E>
): Observable<out E> = this.filter {
    when (it) {
        is E -> true
        else -> false
    }
}.map { it as E }
