package com.dev.gold.awesomelotto.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable


class PermissionManagerImpl(
    private val context: Context
) : PermissionManager {

    override fun checkPermission(permission: String) =
        ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

    override fun requestPermission(
        activity: Activity,
        permission: String
    ): Observable<Boolean> =
        RxPermissions(activity)
            .request(permission)
}
