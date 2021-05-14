package com.slobodyanyuk_mykhailo.testapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.slobodyanyuk_mykhailo.testapp.model.network.ConnectionInterceptor

@Suppress("UNCHECKED_CAST")
class AppViewModelFactory(private val connectionInterceptor: ConnectionInterceptor) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AppViewModel(connectionInterceptor) as T
    }
}