package com.companyname.appname.presentation.feature2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.companyname.appname.presentation.feature2.fragments.rmvvm.LoadRandomActivityAction
import com.companyname.appname.presentation.feature2.fragments.rmvvm.RandomActivityViewModel
import com.companyname.appname.feature_2_presentation.databinding.FragmentRandomActivityBinding
import com.companyname.appname.presentation.common.BaseFragment
import com.companyname.appname.presentation.common.extention.visibility
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers

@AndroidEntryPoint
class RandomActivityFragment : BaseFragment() {

    private var _binding: FragmentRandomActivityBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RandomActivityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRandomActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.actionStream.onNext(LoadRandomActivityAction)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViewModel()
    }

    private fun bindViewModel() = with(viewModel) {
        viewState()
            .observeOn(AndroidSchedulers.mainThread())
            .observe { state -> state.title.let(::handleTitle) }
        progressState()
            .observeOn(AndroidSchedulers.mainThread())
            .observe { state -> state.let(::handleProgress) }
    }

    private fun handleTitle(value: String) {
        binding.title.text = value
    }

    private fun handleProgress(value: Boolean) {
        binding.progress.visibility(value)
    }
}


