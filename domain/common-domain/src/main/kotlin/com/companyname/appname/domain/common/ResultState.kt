package com.companyname.appname.domain.common

import io.reactivex.Flowable

data class ResultState(
    val stateStream: Flowable<State>,
    val request: () -> Unit
)

sealed class State
data class LoadingState(val loading: Boolean) : State()
data class SuccessState(val entity: Entity) : State()
data class ErrorState(val throwable: Throwable) : State()
