package com.companyname.appname.feature1.fragments.first.rmvvm

import com.companyname.appname.presentation.common.Action

sealed class FirstAction : Action

object NextFragmentButtonClickAction : FirstAction()
object AnotherFeatureButtonClickAction : FirstAction()
