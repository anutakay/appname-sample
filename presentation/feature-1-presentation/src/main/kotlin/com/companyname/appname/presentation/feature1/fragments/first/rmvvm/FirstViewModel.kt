package com.companyname.appname.presentation.feature1.fragments.first.rmvvm

import com.companyname.appname.presentation.common.BaseViewModel
import com.companyname.appname.presentation.common.Screens
import com.companyname.appname.presentation.common.extention.filterTo
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    private val screens: Screens,
    private val router: Router
) : BaseViewModel() {

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

    fun navigateViewState(): Flowable<Screen> = navigateViewState.hide()
}
