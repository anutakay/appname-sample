package com.companyname.appname.presentation.common.delegate

import androidx.lifecycle.LifecycleOwner
import com.companyname.appname.presentation.common.RxLifecycleHandler
import io.reactivex.rxjava3.core.Observable

class RxObserverDelegate : IRxObserverDelegate {

    private lateinit var owner: LifecycleOwner

    override fun registerObserverDelegate(owner: LifecycleOwner) {
        this.owner = owner
    }

    override fun <T : Any> Observable<T>.observe(observer: (T) -> Unit) {
        RxLifecycleHandler(owner, this, observer)
    }
}
