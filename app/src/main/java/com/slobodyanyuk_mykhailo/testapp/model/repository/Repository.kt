package com.slobodyanyuk_mykhailo.testapp.model.repository

import com.slobodyanyuk_mykhailo.testapp.model.network.NetworkConnInterceptor
import com.slobodyanyuk_mykhailo.testapp.model.network.TestApi
import com.slobodyanyuk_mykhailo.testapp.model.network.TestApiRequest
import com.slobodyanyuk_mykhailo.testapp.model.responses.LinksResponse

class Repository(private val networkConnInterceptor: NetworkConnInterceptor): TestApiRequest() {

    suspend fun getLinks() : LinksResponse {
       return apiRequest {
           TestApi(networkConnInterceptor).getLinks()
       }
    }
}