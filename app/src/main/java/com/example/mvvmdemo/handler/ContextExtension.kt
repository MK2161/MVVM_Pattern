package com.example.mvvmdemo.handler

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

fun Context.showKeyBoard(view: View) {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED)
}

fun Context.hideKeyBoard(view: View) {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun String?.hasChars(): Boolean {
    return TextUtils.isEmpty(this).isFalse()
}

@OptIn(ExperimentalContracts::class)
fun Boolean?.isFalse(): Boolean {
    contract {
        returns(false) implies (this@isFalse == null)
    }
    return this@isFalse == false
}