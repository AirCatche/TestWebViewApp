package com.slobodyanyuk_mykhailo.testapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.slobodyanyuk_mykhailo.testapp.databinding.FragmentLoadingBinding

class LoadingFragment: Fragment() {

    private var _binding: FragmentLoadingBinding? = null

    // Use between OnCreateView & OnDestroyView
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoadingBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() : LoadingFragment{
            val args: Bundle = Bundle()
            val fragment = LoadingFragment()
            fragment.arguments = args
            return fragment
        }
    }
}