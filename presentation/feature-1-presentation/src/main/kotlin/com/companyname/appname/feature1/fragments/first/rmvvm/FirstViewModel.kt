package com.companyname.appname.feature1.fragments.first.rmvvm

import com.companyname.appname.presentation.common.BaseViewModel
import com.companyname.appname.presentation.common.IScreens
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
    private val screens: IScreens,
    private val router: Router
) : BaseViewModel() {

    private val navigateViewState = PublishProcessor.create<Screen>()

    val welcomeText: String
        get() = "My text"

    init {
        actionStream.filterTo(NextFragmentButtonClickAction::class.java)
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribe(::nextFragmentButtonClicked)
            .track()

        actionStream.filterTo(AnotherFeatureButtonClickAction::class.java)
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribe(::anotherFeatureButtonClick)
            .track()
    }

    private fun nextFragmentButtonClicked(action: NextFragmentButtonClickAction) =
        router.navigateTo(screens.second())

    private fun anotherFeatureButtonClick(action: AnotherFeatureButtonClickAction) =
        router.newRootScreen(screens.feature2())

    fun navigateViewState(): Flowable<Screen> = navigateViewState.hide()
}