package com.dev.gold.awesomelotto.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import androidx.fragment.app.FragmentActivity
import java.text.SimpleDateFormat
import java.util.*


fun View.getActivity(): Activity = context.getActivity()

fun View.getFragmentActivity(): FragmentActivity = getActivity() as FragmentActivity

fun Context.getActivity(): Activity {
    var contextTemp = this
    while (contextTemp is ContextWrapper) {
        if (contextTemp is Activity)
            return contextTemp

        contextTemp = contextTemp.baseContext
    }

    throw ActivityNotFoundException()
}

fun Date?.toStringDate(regex: String) = this?.let {
    SimpleDateFormat(regex, Locale.getDefault()).format(it)
} ?: ""
