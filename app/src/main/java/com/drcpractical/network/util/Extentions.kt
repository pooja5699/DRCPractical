package com.drcpractical.network.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.TextUtils
import android.util.Patterns
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.drcpractical.R
import java.text.DecimalFormat

fun Fragment.showToast(message: String) {
    if (!TextUtils.isEmpty(message)) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}

fun Context.showToast(message: String) {
    if (!TextUtils.isEmpty(message)) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

fun Context.amountFormat(amount: Double): String {
    return DecimalFormat("#,##0.00").format(amount)
}

fun Context.inPhoneFormat(phoneNumber: String?): String {
    var formattedStr = phoneNumber ?: ""
    if (!TextUtils.isEmpty(phoneNumber)) {
        var arrayList = arrayListOf<String>()
        var charArray = formattedStr.toCharArray()
        charArray.forEach { arrayList.add(it.toString()) }
        try {
            arrayList.add(6, "-")
            arrayList.add(3, "-")
        } catch (e: Exception) {
        }
        formattedStr = arrayList.joinToString("")
    } else {
        formattedStr = "Not Available"
    }
    return formattedStr
}

fun Fragment.isValidEmail(email: String): Boolean {
    return email.matches(Regex(Patterns.EMAIL_ADDRESS.pattern()))
}

fun Fragment.isStrongPassword(password: String): Boolean {
    val passwordPattern =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*+=])(?=\\S+$).{8,25}$"
    return password.matches(Regex(passwordPattern))
}

fun Fragment.hideKeyboard() {
    val view = requireActivity().currentFocus
    if (view != null) {
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun CharSequence?.ifNullOrEmpty(default: String) =
    if (this.isNullOrEmpty()) default else this.toString()


fun isInternetAvailable(mContext: Context): Boolean {
    var result = false
    val connectivityManager =
        mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    connectivityManager?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        } else {
            return it.activeNetworkInfo != null && it.activeNetworkInfo!!.isConnected

        }
    }

    return result
}

