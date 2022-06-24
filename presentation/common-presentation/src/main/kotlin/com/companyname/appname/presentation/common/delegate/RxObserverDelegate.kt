package com.companyname.appname.presentation.common.delegate

import androidx.lifecycle.LifecycleOwner
import com.companyname.appname.presentation.common.extention.observe
import io.reactivex.Flowable

class RxObserverDelegate : IRxObserverDelegate {

    private lateinit var owner: LifecycleOwner

    override fun registerObserverDelegate(owner: LifecycleOwner) {
        this.owner = owner
    }

    override fun <T> Flowable<T>.observe(observer: (T) -> Unit) {
        observe(owner, observer)
    }
}
