package com.dev.gold.awesomelotto.utils

import android.app.Activity
import io.reactivex.Observable


interface PermissionManager {

    fun checkPermission(
        permission: String
    ) : Boolean

    fun requestPermission(
        activity: Activity,
        permission: String
    ): Observable<Boolean>
}
