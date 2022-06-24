package com.companyname.appname.presentation.common

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

class RxLifecycleHandler<T>(
    owner: LifecycleOwner,
    private val observable: Observable<T>,
    private val observer: (T) -> Unit
) : DefaultLifecycleObserver {
    private val lifecycle = owner.lifecycle
    private var disposable: Disposable? = null

    init {
        if (lifecycle.currentState != Lifecycle.State.DESTROYED) {
            owner.lifecycle.addObserver(this)
            observeIfPossible()
        }
    }

    private fun observeIfPossible() {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            disposable ?: let {
                disposable = observable.subscribe { data -> observer(data) }
            }
        }
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        observeIfPossible()
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        disposable?.dispose()
        disposable = null
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        lifecycle.removeObserver(this)
    }
}
