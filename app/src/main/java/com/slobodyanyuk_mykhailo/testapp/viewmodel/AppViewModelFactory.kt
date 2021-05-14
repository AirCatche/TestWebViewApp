package com.slobodyanyuk_mykhailo.testapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.slobodyanyuk_mykhailo.testapp.model.network.NetworkConnInterceptor

@Suppress("UNCHECKED_CAST")
class AppViewModelFactory(private val networkConnInterceptor: NetworkConnInterceptor) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AppViewModel(networkConnInterceptor) as T
    }
}