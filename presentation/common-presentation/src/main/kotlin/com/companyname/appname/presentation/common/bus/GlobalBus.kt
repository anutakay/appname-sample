package com.companyname.appname.presentation.common.bus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object GlobalBus {
    private val actionStream = PublishSubject.create<GlobalBusAction>()

    fun sendAction(value: GlobalBusAction) {
        actionStream.onNext(value)
    }

    val actions: Observable<GlobalBusAction>
        get() = actionStream
}
