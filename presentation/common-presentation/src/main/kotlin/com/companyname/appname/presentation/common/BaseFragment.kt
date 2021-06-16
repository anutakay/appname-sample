package com.companyname.appname.presentation.common

import androidx.fragment.app.Fragment
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragment : Fragment() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun Disposable.track() {
        compositeDisposable.add(this)
    }

    protected fun <T> Flowable<T>.observe(o: (T) -> Unit) {
        RxLifecycleHandler(this@BaseFragment, this, o)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}
