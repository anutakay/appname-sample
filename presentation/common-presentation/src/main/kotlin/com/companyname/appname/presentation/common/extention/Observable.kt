package com.companyname.appname.presentation.common.extention

import io.reactivex.rxjava3.core.Observable

inline fun <T : Any, reified E : T> Observable<in T>.filterTo(
    @Suppress("UNUSED_PARAMETER") target: Class<E>
): Observable<out E> = this.filter {
    when (it) {
        is E -> true
        else -> false
    }
}.map { it as E }
