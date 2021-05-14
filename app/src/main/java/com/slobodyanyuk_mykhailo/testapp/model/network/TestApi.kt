package com.slobodyanyuk_mykhailo.testapp.model.network

import com.slobodyanyuk_mykhailo.testapp.model.responses.LinksResponse
import okhttp3.OkHttpClient

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface TestApi{
    companion object {
        operator fun invoke(networkConnInterceptor: NetworkConnInterceptor) : TestApi {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://efs5i1ube5.execute-api.eu-central-1.amazonaws.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TestApi::class.java)
        }
    }
    @GET("/prod")
    @Headers("Content-Type: application/json")
    suspend fun getLinks() : Response<LinksResponse>
}