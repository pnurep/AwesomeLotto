package com.dev.gold.awesomelotto.utils

import android.util.Log


object Logger {

    private var lineNumber: Int = 0
    private var className: String? = null
    private var methodName: String? = null

    var isDebuggable = false

    fun logException(e: Throwable) {
        if (isDebuggable) e.printStackTrace()
    }

    private fun createLog(log: String) = "[$methodName:$lineNumber]$log"

    private fun getMethodNames(sElements: Array<StackTraceElement>) = sElements[1].let { element ->
        className = element.fileName.replace(".java|.kt".toRegex(), "")
        methodName = element.methodName
        lineNumber = element.lineNumber
    }

    fun e(message: String) {
        if (!isDebuggable) return

        getMethodNames(Throwable().stackTrace)

        Log.e(className, createLog(message))
    }

    fun i(message: String) {
        if (!isDebuggable) return

        getMethodNames(Throwable().stackTrace)

        Log.i(className, createLog(message))
    }

    fun d(message: String) {
        if (!isDebuggable) return

        getMethodNames(Throwable().stackTrace)

        Log.d(className, createLog(message))
    }

    fun v(message: String) {
        if (!isDebuggable) return

        getMethodNames(Throwable().stackTrace)

        Log.v(className, createLog(message))
    }

    fun w(message: String) {
        if (!isDebuggable) return

        getMethodNames(Throwable().stackTrace)

        Log.w(className, createLog(message))
    }
}
