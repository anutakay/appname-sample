package com.companyname.appname.presentation.common.delegate

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

class RxTrackDelegate : IRxTrackDelegate {

    private val compositeDisposable = CompositeDisposable()

    override fun Disposable.track() {
        compositeDisposable.add(this)
    }

    override fun clearTracked() = compositeDisposable.clear()
}
