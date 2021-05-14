package com.slobodyanyuk_mykhailo.testapp.model.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.slobodyanyuk_mykhailo.testapp.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectionInterceptor(context: Context) : Interceptor {
    private val appContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (noInternetConnection()) {
            throw NoInternetException("Please check active data connection")
        }
        return chain.proceed(chain.request())
    }
    private fun noInternetConnection() : Boolean {
        var result = true
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return true
            val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return true
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> false
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> false
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> false
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> false
                        ConnectivityManager.TYPE_MOBILE -> false
                        ConnectivityManager.TYPE_ETHERNET -> false
                        else -> true
                    }
                }
            }
        }
        return result
    }
}