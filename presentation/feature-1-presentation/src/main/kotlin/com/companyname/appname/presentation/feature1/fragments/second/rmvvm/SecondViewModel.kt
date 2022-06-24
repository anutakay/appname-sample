package com.companyname.appname.presentation.feature1.fragments.second.rmvvm

import androidx.lifecycle.ViewModel
import com.companyname.appname.presentation.common.Action
import com.companyname.appname.presentation.common.delegate.IRxTrackDelegate
import com.companyname.appname.presentation.common.delegate.RxTrackDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor() : ViewModel(),
    IRxTrackDelegate by RxTrackDelegate() {

    val actionStream: PublishSubject<Action> = PublishSubject.create()

    override fun onCleared() {
        super.onCleared()
        clearTracked()
    }
}
