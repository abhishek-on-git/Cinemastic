package com.wcp.cinemastic.util

import android.util.Log

class Logger {
    companion object {
        val TAG = "Cinemastic"

        fun logd(module: String, message: String) {
            Log.d("$TAG.$module", message)
        }
        fun loge(module: String, message: String) {
            Log.d("$TAG.$module", message)
        }
    }
}