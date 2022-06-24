package com.companyname.appname.presentation.common.delegate

import io.reactivex.rxjava3.disposables.Disposable


interface IRxTrackDelegate {
    fun Disposable.track()
    fun clearTracked()
}
