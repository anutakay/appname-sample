package com.companyname.appname.presentation.feature2.fragments.rmvvm

import com.companyname.appname.presentation.common.Action

sealed class RandomActivityAction : Action

object LoadRandomActivityAction : RandomActivityAction()
