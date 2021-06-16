package com.companyname.appname.feature1.fragments.second.rmvvm

import com.companyname.appname.presentation.common.Action

sealed class SecondAction : Action

object BackButtonClickedAction : SecondAction()
