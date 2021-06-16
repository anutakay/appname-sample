package com.companyname.appname.feature1.fragments.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.companyname.appname.feature1.databinding.FragmentFirstBinding
import com.companyname.appname.feature1.fragments.first.rmvvm.AnotherFeatureButtonClickAction
import com.companyname.appname.feature1.fragments.first.rmvvm.FirstViewModel
import com.companyname.appname.feature1.fragments.first.rmvvm.NextFragmentButtonClickAction
import com.companyname.appname.presentation.common.BaseFragment
import com.github.terrakok.cicerone.Screen
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers

@AndroidEntryPoint
class FirstFragment : BaseFragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FirstViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        welcomeLabel.text = viewModel.welcomeText
        nextFragmentButton.setOnClickListener {
            viewModel.actionStream.onNext(NextFragmentButtonClickAction)
        }
        anotherFeatureButton.setOnClickListener {
            viewModel.actionStream.onNext(AnotherFeatureButtonClickAction)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.nextFragmentButton.setOnClickListener(null)
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViewModel()
    }

    private fun bindViewModel() = with(viewModel) {
        navigateViewState()
            .observeOn(AndroidSchedulers.mainThread())
            .observe(::handleNavigate)
    }

    private fun handleNavigate(screen: Screen) {

    }
}
