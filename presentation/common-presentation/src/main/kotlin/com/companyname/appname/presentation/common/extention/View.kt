package com.companyname.appname.presentation.common.extention

import android.view.View

fun View.visibility(value: Boolean) {
    visibility = when (value) {
        true -> View.VISIBLE
        else -> View.GONE
    }
}
