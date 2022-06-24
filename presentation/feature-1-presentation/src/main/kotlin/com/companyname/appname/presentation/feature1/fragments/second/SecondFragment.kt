package com.companyname.appname.presentation.feature1.fragments.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.companyname.appname.feature1.databinding.FragmentSecondBinding
import com.companyname.appname.presentation.common.delegate.IRxObserverDelegate
import com.companyname.appname.presentation.common.delegate.RxObserverDelegate
import com.companyname.appname.presentation.feature1.fragments.second.rmvvm.SecondViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : Fragment(),
    IRxObserverDelegate by RxObserverDelegate() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SecondViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerObserverDelegate(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        myButton.setOnClickListener {}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.myButton.setOnClickListener(null)
        _binding = null
    }
}
