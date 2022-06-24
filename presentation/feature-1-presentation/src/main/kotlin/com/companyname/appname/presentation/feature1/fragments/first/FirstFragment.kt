package com.companyname.appname.presentation.feature1.fragments.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.companyname.appname.feature1.databinding.FragmentFirstBinding
import com.companyname.appname.presentation.common.delegate.IRxObserverDelegate
import com.companyname.appname.presentation.common.delegate.RxObserverDelegate
import com.companyname.appname.presentation.feature1.fragments.first.rmvvm.AnotherFeatureButtonClickAction
import com.companyname.appname.presentation.feature1.fragments.first.rmvvm.FirstViewModel
import com.companyname.appname.presentation.feature1.fragments.first.rmvvm.NextFragmentButtonClickAction
import com.github.terrakok.cicerone.Screen
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

@AndroidEntryPoint
class FirstFragment : Fragment(),
    IRxObserverDelegate by RxObserverDelegate() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FirstViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerObserverDelegate(this)
        bindViewModel()
    }

    private fun bindViewModel() = with(viewModel) {
        navigateViewState()
            .observeOn(AndroidSchedulers.mainThread())
            .observe { handleNavigate(it) }
    }

    private fun handleNavigate(screen: Screen) {
        TODO("Not yet implemented")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.nextFragmentButton.setOnClickListener(null)
        binding.anotherFeatureButton.setOnClickListener(null)
        _binding = null
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
}
