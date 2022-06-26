package com.companyname.appname.presentation.feature2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.companyname.appname.presentation.common.delegate.IRxObserverDelegate
import com.companyname.appname.presentation.common.delegate.RxObserverDelegate
import com.companyname.appname.presentation.feature2.fragments.rmvvm.LoadRandomActivityAction
import com.companyname.appname.presentation.feature2.fragments.rmvvm.RandomActivityBinder
import com.companyname.appname.presentation.feature2.fragments.rmvvm.RandomActivityView
import com.companyname.appname.presentation.feature2.fragments.rmvvm.RandomActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomActivityFragment : Fragment(),
    IRxObserverDelegate by RxObserverDelegate() {

    private val randomActivityView = RandomActivityView()
    private val viewModel: RandomActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerObserverDelegate(this)
        RandomActivityBinder.bindViewModel(viewModel, randomActivityView, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = randomActivityView.inflate(inflater, container, false)

    override fun onDestroyView() {
        super.onDestroyView()
        randomActivityView.clear()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.actionStream.onNext(LoadRandomActivityAction)
    }
}
