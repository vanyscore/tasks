package com.vanyscore.app.utils

import android.util.Log

object Logger {
    private const val TAG = "tasks_debug"

    fun log(message: String) {
        Log.d(TAG, message)
    }
}