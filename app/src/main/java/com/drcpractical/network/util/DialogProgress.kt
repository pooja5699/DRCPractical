package com.drcpractical.network.util

import android.app.AlertDialog
import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.drcpractical.R

class DialogProgress : AlertDialog {
    var mContext: Context? = null
    var message = ""

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, theme: Int) : super(context, theme) {
        mContext = context
    }

    constructor(context: Context?, theme: Int, msg: String) : super(context, theme) {
        mContext = context
        message = msg
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val wlmp = window!!.attributes
        wlmp.gravity = Gravity.CENTER_HORIZONTAL
        window!!.attributes = wlmp
        setTitle(null)
        setCancelable(false)
        setOnCancelListener(null)
        val view: View = LayoutInflater.from(mContext).inflate(
            R.layout.progress_dialog, null
        )
        val mProgressSpin = view.findViewById<ProgressBar>(R.id.progressBar2)

        // To support API version 21 and above
        try {
            mProgressSpin.indeterminateDrawable
                .setColorFilter(
                    ContextCompat.getColor(context, R.color.magenta),
                    PorterDuff.Mode.SRC_IN
                )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val tvMessage = view.findViewById<TextView>(R.id.tv_message)
        if (TextUtils.isEmpty(message)) {
            tvMessage.visibility = View.GONE
        } else {
            tvMessage.text = message
            tvMessage.visibility = View.VISIBLE
        }
        setContentView(view)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }
}