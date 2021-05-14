package com.slobodyanyuk_mykhailo.testapp.model.network

import com.slobodyanyuk_mykhailo.testapp.utils.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class TestApiRequest {
    suspend fun <T: Any> apiRequest(call: suspend () -> Response<T>) : T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.string()
            val errorMessage = StringBuilder()
            error?.let {
                try {
                    errorMessage.append(JSONObject(it).getString("errorMessage"))
                } catch (ignored: JSONException) { }
                 errorMessage.append("\n")
            }
            errorMessage.append("Error code: ${response.code()}")
            throw ApiException(errorMessage.toString())
        }
    }
}