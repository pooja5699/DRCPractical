package com.drcpractical.network

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.drcpractical.AppClass
import com.drcpractical.network.model.HomeRequest
import com.drcpractical.network.model.HomeResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {

    @POST("homepage")
    fun homepage(@Body request: HomeRequest): Call<ArrayList<HomeResponse>>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): APIService {

            var okkHttpclientBuilder = OkHttpClient.Builder()
            okkHttpclientBuilder.addInterceptor(networkConnectionInterceptor)
            okkHttpclientBuilder.addInterceptor(Interceptor { chain ->
                val original = chain.request()
                var requestBuilder: Request.Builder = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())

                val request = requestBuilder.build()
                val response = chain.proceed(request)
                response
            })

            okkHttpclientBuilder.addInterceptor(
                ChuckerInterceptor.Builder(AppClass.getInstance())
                    .collector(ChuckerCollector(AppClass.getInstance()))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )


            return Retrofit.Builder()
                .client(okkHttpclientBuilder.build())
                .baseUrl("https://mobileapp.annabelleme.com/en/rest/V1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)


        }
    }

}

