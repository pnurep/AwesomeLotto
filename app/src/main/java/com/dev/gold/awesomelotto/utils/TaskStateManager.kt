package com.dev.gold.awesomelotto.utils

import android.app.Activity
import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import java.lang.ref.WeakReference
import java.util.ArrayDeque


object TaskStateManager {

    private val activityStackList by lazy { ArrayDeque<WeakReference<Activity>>() }

    private val listeners by lazy { ArrayDeque<OnTaskChangeListener>() }

    private var isForeground = false

    private var broadcastReceiver: BroadcastReceiver? = null

    private var isInit = false


    fun init(context: Context) {
        if (isInit) return

        isInit = true

        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
            addAction(Intent.ACTION_USER_PRESENT)
        }

        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                if (intent.action == Intent.ACTION_CLOSE_SYSTEM_DIALOGS && isForeground) deliverBackground()
            }
        }

        context.registerReceiver(broadcastReceiver, intentFilter)
    }

    fun clear(context: Context) {
        if (!isInit) return

        context.unregisterReceiver(broadcastReceiver)
        broadcastReceiver = null
        isForeground = false
        activityStackList.clear()
        listeners.clear()
        isInit = false
    }

    fun isForeground(): Boolean = isForeground

    fun addOnTaskChangeListener(listener: OnTaskChangeListener) {
        if (!listeners.contains(listener)) listeners.add(listener)
    }

    fun removeOnTaskChangeListener(listener: OnTaskChangeListener) {
        if (listeners.isNotEmpty()) listeners.remove(listener)
    }

    fun onCreate(activity: Activity) {
        activityStackList.add(WeakReference(activity))
    }

    fun onNewIntent(activity: Activity) {
        activityStackList.clone()
            .firstOrNull { wkActivity ->
                activity == wkActivity.get()
            }
            ?.let { wkActivity ->
                if (activityStackList.isNotEmpty()) activityStackList.remove(wkActivity)
                activityStackList.add(wkActivity)
            }
    }

    fun onResume() {
        if (!isForeground) deliverForeground()
    }

    fun onStop(context: Context) {
        if (!isForeground) return

        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningProcesses = am.runningAppProcesses

        val isBackground = runningProcesses
            ?.filter { processInfo ->
                processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
            }
            ?.flatMap { processInfo -> processInfo.pkgList.toList() }
            ?.any { activeProcess ->
                activeProcess == context.packageName
            } == false

        if (isBackground) deliverBackground()
    }

    private fun deliverBackground() {
        isForeground = false
        listeners.clone().forEach { target ->
            target.onTaskBackground()
        }
    }

    fun onDestroy(activity: Activity) {
        activityStackList.clone()
            .firstOrNull { wkActivity ->
                activity == wkActivity.get()
            }
            ?.let { wkActivity ->
                activityStackList.remove(wkActivity)
            }
    }

    private fun deliverForeground() {
        isForeground = true
        listeners.clone().forEach { target ->
            target.onTaskForeground()
        }
    }
}

interface OnTaskChangeListener {
    fun onTaskForeground()
    fun onTaskBackground()
}
