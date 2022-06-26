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
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class RandomActivityViewModel @Inject constructor(
    private val getRandomActivity: GetRandomActivity
) : ViewModel(),
    IRxTrackDelegate by RxTrackDelegate() {

    val actionStream: PublishSubject<Action> = PublishSubject.create()

    private val results = BehaviorSubject.createDefault(RandomActivityViewState(""))

    private val progressState = BehaviorProcessor.createDefault(false)

    init {
        actionStream.filterTo(LoadRandomActivityAction::class.java)
            .throttleFirst(1, TimeUnit.SECONDS)
            .concatMap { getRandomActivity.instance().execute() }
            .map { RandomActivityViewState("", result = it) }
            .subscribe(results)
    }

    fun viewState(): Observable<RandomActivityViewState> =
        results.scan { currentState, newResult ->
            newResult.result?.let {
                when (it) {
                    is LCE.Content -> currentState.copy(title = it.data.activity)
                    is LCE.Error -> currentState
                    LCE.Loading -> currentState
                }
            } ?: currentState
        }

    fun progressState(): Observable<Boolean> = progressState.toObservable()

    override fun onCleared() {
        super.onCleared()
        clearTracked()
    }
}
