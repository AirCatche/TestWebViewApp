package com.slobodyanyuk_mykhailo.testapp.model.repository

import com.slobodyanyuk_mykhailo.testapp.model.network.ConnectionInterceptor
import com.slobodyanyuk_mykhailo.testapp.model.network.TestApi
import com.slobodyanyuk_mykhailo.testapp.model.network.TestApiRequest
import com.slobodyanyuk_mykhailo.testapp.model.responses.LinksResponse

class Repository(private val connection: ConnectionInterceptor): TestApiRequest() {

    suspend fun getLinks() : LinksResponse = apiRequest {
        TestApi(connection).getLinks()
    }
}