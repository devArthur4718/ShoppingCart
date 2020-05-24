package com.devarthur4718.mvp.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

fun Context.CircularProgress(): CircularProgressDrawable {

    val circularProgressDrawable = CircularProgressDrawable(this)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    return circularProgressDrawable
}

fun Context.showKeyboard() {
    val imm: InputMethodManager =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Context.hideKeyBoard(view : View){
    val imm =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm!!.hideSoftInputFromWindow(view.getWindowToken(), 0)
}