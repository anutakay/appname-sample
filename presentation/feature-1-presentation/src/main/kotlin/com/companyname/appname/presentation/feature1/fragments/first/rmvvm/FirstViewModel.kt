package com.companyname.appname.presentation.feature1.fragments.first.rmvvm

import androidx.lifecycle.ViewModel
import com.companyname.appname.presentation.common.Action
import com.companyname.appname.presentation.common.Screens
import com.companyname.appname.presentation.common.delegate.IRxTrackDelegate
import com.companyname.appname.presentation.common.delegate.RxTrackDelegate
import com.companyname.appname.presentation.common.extention.filterTo
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.processors.PublishProcessor
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    private val screens: Screens,
    private val router: Router
) : ViewModel(),
    IRxTrackDelegate by RxTrackDelegate() {

    val actionStream: PublishSubject<Action> = PublishSubject.create()

    private val navigateViewState = PublishProcessor.create<Screen>()

    val welcomeText: String
        get() = "My text"

    init {
        actionStream.filterTo(NextFragmentButtonClickAction::class.java)
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribe { nextFragmentButtonClicked() }
            .track()

        actionStream.filterTo(AnotherFeatureButtonClickAction::class.java)
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribe { anotherFeatureButtonClick() }
            .track()
    }

    private fun nextFragmentButtonClicked() = router.navigateTo(screens.second())
    private fun anotherFeatureButtonClick() = router.newRootScreen(screens.feature2())

    fun navigateViewState(): Observable<Screen> = navigateViewState.toObservable()

    override fun onCleared() {
        super.onCleared()
        clearTracked()
    }
}
