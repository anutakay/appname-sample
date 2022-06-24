package com.companyname.appname.presentation.common.delegate

import io.reactivex.disposables.Disposable

interface IRxTrackDelegate {
    fun Disposable.track()
    fun clearTracked()
}
