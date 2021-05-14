package com.slobodyanyuk_mykhailo.testapp.viewmodel
import androidx.lifecycle.ViewModel
import com.slobodyanyuk_mykhailo.testapp.model.repository.Repository
import com.slobodyanyuk_mykhailo.testapp.model.network.ConnectionInterceptor
import com.slobodyanyuk_mykhailo.testapp.utils.ApiException
import com.slobodyanyuk_mykhailo.testapp.utils.Coroutines
import com.slobodyanyuk_mykhailo.testapp.utils.NoInternetException
import java.net.SocketTimeoutException

class AppViewModel(private val connectionInterceptor: ConnectionInterceptor): ViewModel() {
    var loadingListener: OnLoadingListener? = null
    fun fetchData() {
        Coroutines.network {
            try {
                val response = Repository(connectionInterceptor).getLinks()
                loadingListener?.onLoadingComplete(response)
            }catch (e: ApiException) {
                loadingListener?.onLoadingFailure(e.message!!)
            }catch (e: NoInternetException) {
                loadingListener?.onLoadingFailure(e.message!!)
            }catch (e: SocketTimeoutException) {
                loadingListener?.onLoadingFailure("No respond from server, try again")
            }
        }
    }
}