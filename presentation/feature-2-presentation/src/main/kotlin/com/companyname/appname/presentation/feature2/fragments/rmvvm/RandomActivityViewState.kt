package com.companyname.appname.presentation.feature2.fragments.rmvvm

import com.companyname.appname.domain.bored.entities.Activity
import com.companyname.appname.domain.common.LCE
import com.companyname.appname.presentation.common.ViewState

data class RandomActivityViewState(
    val title: String,
    val result: LCE<Activity>? = null
) : ViewState
