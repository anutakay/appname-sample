package com.companyname.appname.presentation.feature2.fragments.rmvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import com.companyname.appname.feature_2_presentation.databinding.FragmentRandomActivityBinding
import com.companyname.appname.presentation.common.extention.visibility

class RandomActivityView {
    var binding: FragmentRandomActivityBinding? = null

    fun inflate(inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean) =
        FragmentRandomActivityBinding.inflate(inflater, container, attachToParent)
            .also { binding = it }
            .root

    fun clear() {
        binding = null
    }

    fun handleTitle(value: String) {
        binding?.title?.text = value
    }

    fun handleProgress(value: Boolean) {
        binding?.progress?.visibility(value)
    }
}
