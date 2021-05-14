package com.slobodyanyuk_mykhailo.testapp.viewmodel

import com.slobodyanyuk_mykhailo.testapp.model.responses.LinksResponse

interface OnLoadingListener {
    fun onLoadingComplete(links: LinksResponse)
    fun onLoadingFailure(message: String)
}