package com.drcpractical.network

import android.content.Context
import android.util.Log
import com.drcpractical.AppClass
import com.drcpractical.R
import com.drcpractical.network.util.NoInternetException
import com.drcpractical.network.util.isInternetAvailable
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(
    context: Context
) : Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("TAG", "internet interceptor")
        if (!isInternetAvailable(applicationContext))
            throw NoInternetException(AppClass.getInstance().getString(R.string.connection_error))
        return chain.proceed(chain.request())
    }


}