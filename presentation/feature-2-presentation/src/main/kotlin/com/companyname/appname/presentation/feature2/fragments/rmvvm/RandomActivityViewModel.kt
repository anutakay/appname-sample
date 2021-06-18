package com.companyname.appname.presentation.feature2.fragments.rmvvm

import androidx.lifecycle.viewModelScope
import com.companyname.appname.domain.bored.usecases.GetRandomActivityUseCase
import com.companyname.appname.domain.common.Result
import com.companyname.appname.presentation.common.BaseViewModel
import com.companyname.appname.presentation.common.extention.filterTo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class RandomActivityViewModel @Inject constructor(
    val getRandomActivity: GetRandomActivityUseCase
) : BaseViewModel() {

    private val viewState by lazy {
        val processor = BehaviorProcessor.create<RandomActivityViewState>()
        processor.onNext(RandomActivityViewState(title = ""))
        processor
    }

    private val progressState by lazy {
        val processor = BehaviorProcessor.create<Boolean>()
        processor.onNext(false)
        processor
    }

    init {
        actionStream.filterTo(LoadRandomActivityAction::class.java)
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribe(::loadRandomActivity)
            .track()
    }

    @Suppress("UNUSED_PARAMETER")
    private fun loadRandomActivity(action: LoadRandomActivityAction) {
        progressState.onNext(true)

        viewModelScope.launch {
            val result = getRandomActivity()
            if (result is Result.Success) {
                viewState.onNext(viewState.value?.copy(title = result.data.activity))
            }
            progressState.onNext(false)
        }
    }

    fun viewState(): Flowable<RandomActivityViewState> = viewState.hide()

    fun progressState(): Flowable<Boolean> = progressState.hide()
}
