package com.dev.gold.awesomelotto.utils

import android.util.Log


object Logger {

    private var lineNumber: Int = 0
    private var className: String? = null
    private var methodName: String? = null

    var isDebuggable = false

    var additionalLog: ((mode: Int, className: String?, msg: String) -> Unit)? = null
    var additionalLogException: ((Throwable) -> Unit)? = null

    private fun log(mode: Int, className: String?, msg: String) {
        if (isDebuggable) return
        additionalLog?.invoke(mode, className, msg)
    }

    /**
     * Exceptions
     */
    fun logException(e: Throwable) {
        if (isDebuggable) e.printStackTrace()
        additionalLogException?.invoke(e)
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
        log(Log.ERROR, className, createLog(message))
    }

    fun i(message: String) {
        if (!isDebuggable) return

        getMethodNames(Throwable().stackTrace)

        Log.i(className, createLog(message))
        log(Log.INFO, className, createLog(message))
    }

    fun d(message: String) {
        if (!isDebuggable) return

        getMethodNames(Throwable().stackTrace)

        Log.d(className, createLog(message))
        log(Log.DEBUG, className, createLog(message))
    }

    fun v(message: String) {
        if (!isDebuggable) return

        getMethodNames(Throwable().stackTrace)

        Log.v(className, createLog(message))
        log(Log.VERBOSE, className, createLog(message))
    }

    fun w(message: String) {
        if (!isDebuggable) return

        getMethodNames(Throwable().stackTrace)

        Log.w(className, createLog(message))
        log(Log.WARN, className, createLog(message))
    }

}