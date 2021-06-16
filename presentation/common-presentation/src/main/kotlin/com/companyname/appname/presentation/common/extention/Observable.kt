package com.companyname.appname.presentation.common.extention

import io.reactivex.Observable

inline fun <T, reified E : T> Observable<in T>.filterTo(
    @Suppress("UNUSED_PARAMETER") target: Class<E>
): Observable<out E> = this.filter {
    when (it) {
        is E -> true
        else -> false
    }
}.map { it as E }
