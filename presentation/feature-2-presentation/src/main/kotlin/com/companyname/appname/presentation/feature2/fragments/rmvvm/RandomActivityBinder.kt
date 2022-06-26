package com.companyname.appname.presentation.feature2.fragments.rmvvm

import com.companyname.appname.presentation.common.delegate.IRxObserverDelegate
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

object RandomActivityBinder {

    fun bindViewModel(
        viewModel: RandomActivityViewModel,
        view: RandomActivityView,
        rxDelegate: IRxObserverDelegate
    ) = with(rxDelegate) {
        viewModel.viewState()
            .observeOn(AndroidSchedulers.mainThread())
            .observe { state -> state.title.let(view::handleTitle) }
        viewModel.progressState()
            .observeOn(AndroidSchedulers.mainThread())
            .observe { state -> state.let(view::handleProgress) }
    }
}
