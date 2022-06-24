package com.companyname.appname.presentation.feature2.fragments.rmvvm

import androidx.lifecycle.viewModelScope
import com.companyname.appname.domain.bored.usecases.GetRandomActivityUseCase
import com.companyname.appname.domain.common.Result
import com.companyname.appname.domain.common.succeeded
import com.companyname.appname.presentation.common.BaseViewModel
import com.companyname.appname.presentation.common.extention.filterTo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.processors.BehaviorProcessor
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class RandomActivityViewModel @Inject constructor(
    val getRandomActivity: GetRandomActivityUseCase
) : BaseViewModel() {

    private val viewState by lazy {
        BehaviorProcessor.createDefault((RandomActivityViewState(title = "")))
    }

    private val progressState by lazy {
        BehaviorProcessor.createDefault(false)
    }

    init {
        actionStream.filterTo(LoadRandomActivityAction::class.java)
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribe { loadRandomActivity() }
            .track()
    }

    private fun loadRandomActivity() {
        viewModelScope.launch {
            progressState.onNext(true)
            when (val result = getRandomActivity()) {
                is Result.Success ->
                    viewState.onNext(viewState.value?.copy(title = result.data.activity))
                is Result.Error -> TODO()
                Result.Loading -> TODO()
            }
            progressState.onNext(false)
        }
    }

    fun viewState(): Observable<RandomActivityViewState> = viewState.toObservable()
    fun progressState(): Observable<Boolean> = progressState.toObservable()
}
