package com.companyname.appname.presentation.feature2.fragments.rmvvm

import androidx.lifecycle.ViewModel
import com.companyname.appname.domain.bored.usecases.GetRandomActivity
import com.companyname.appname.domain.common.LCE
import com.companyname.appname.presentation.common.Action
import com.companyname.appname.presentation.common.delegate.IRxTrackDelegate
import com.companyname.appname.presentation.common.delegate.RxTrackDelegate
import com.companyname.appname.presentation.common.extention.filterTo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.processors.BehaviorProcessor
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class RandomActivityViewModel @Inject constructor(
    private val getRandomActivity: GetRandomActivity
) : ViewModel(),
    IRxTrackDelegate by RxTrackDelegate() {

    val actionStream: PublishSubject<Action> = PublishSubject.create()

    private val viewState by lazy {
        BehaviorProcessor.createDefault((RandomActivityViewState(title = "")))
    }

    private val progressState by lazy {
        BehaviorProcessor.createDefault(false)
    }

    init {
        actionStream.filterTo(LoadRandomActivityAction::class.java)
            .throttleFirst(1, TimeUnit.SECONDS)
            .flatMap { getRandomActivity.instance().execute() }
            .subscribe {
                when (it) {
                    is LCE.Content -> viewState.onNext(viewState.value!!.copy(title = it.data.activity))
                    is LCE.Error -> TODO()
                    LCE.Loading -> TODO()
                }
            }
            .track()
    }

    fun viewState(): Observable<RandomActivityViewState> = viewState.toObservable()
    fun progressState(): Observable<Boolean> = progressState.toObservable()

    override fun onCleared() {
        super.onCleared()
        clearTracked()
    }
}
