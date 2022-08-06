package com.drcpractical.network

import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.drcpractical.AppClass
import com.drcpractical.R
import com.drcpractical.network.util.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import java.net.HttpURLConnection


abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Call<T>): T {
        val response = call.invoke().execute()
        when {
            response.isSuccessful -> {
                return response.body()!!
            }
            response.code() == HttpURLConnection.HTTP_UNAUTHORIZED -> {
                val intent = Intent("HTTP_UNAUTHORIZED")
                LocalBroadcastManager.getInstance(AppClass.getInstance()).sendBroadcast(intent)
                val error = response.errorBody()?.string()
                val message = StringBuilder()
                error?.let {
                    try {
                        message.append(JSONObject(it).getString("Message"))
                    } catch (e: JSONException) {
                    }
                    message.append("\n")
                }
                message.append("Error Code: ${response.code()}")
                throw ApiException(message.toString())
            }
            else -> {
                val error = response.errorBody()?.string()
                val headerMessage = response.headers()
                val message = StringBuilder()
                error?.let {
                    try {
                        if (!JSONObject(it).getString("Message").isNullOrEmpty()) {
                            message.append(JSONObject(it).getString("Message"))
                        } else {
                            if (headerMessage["Message"].isNullOrEmpty()) {
                                message.append(headerMessage["Message"])
                            } else {
                                message.append(
                                    AppClass.getInstance().getString(R.string.server_not_responding)
                                )
                            }
                        }
                    } catch (e: JSONException) {
                        try {
                            if (!headerMessage["Message"].isNullOrEmpty()) {
                                message.append(headerMessage["Message"])
                            } else {
                                message.append(
                                    AppClass.getInstance().getString(R.string.server_not_responding)
                                )
                            }
                        } catch (e: JSONException) {
                        }
                    }

                }
                if (message.isEmpty()) {
                    message.append(
                        AppClass.getInstance().getString(R.string.server_not_responding)
                    )
                }
                throw ApiException(message.toString())
            }
        }
    }

}