package com.devarthur4718.mvp.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


fun Context.checkInternetAndCall(function: () -> Unit) {
    if (isOnline())
        function()
    else
        onInternetConnectionError()
}

fun Context.isOnline(): Boolean {
    val conMgr = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (conMgr.getNetworkInfo(0) != null) {
        if (conMgr.getNetworkInfo(0).state == NetworkInfo.State.CONNECTED || conMgr.getNetworkInfo(1).state == NetworkInfo.State.CONNECTED) {
            return true
            // notify user you are online
        } else if (conMgr.getNetworkInfo(0).state == NetworkInfo.State.DISCONNECTED || conMgr.getNetworkInfo(1).state == NetworkInfo.State.DISCONNECTED) {
            // notify user you are not online
            return false
        }
    } else {
        if (conMgr.getNetworkInfo(1).state == NetworkInfo.State.CONNECTED) {
            return true
            // notify user you are online
        } else if (conMgr.getNetworkInfo(1).state == NetworkInfo.State.DISCONNECTED) {
            // notify user you are not online
            return false
        }
    }
    return false
}

fun Context.onInternetConnectionError() {
    TODO("Not implemented")
//    showCustomErrorDialog(R.string.empty, R.string.msg_error_wifi_retry)
}