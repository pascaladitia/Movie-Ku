package com.pascal.movieku.utils

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import com.pascal.movieku.R

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.showAlert(title: String, msg: String) {
    AlertDialog.Builder(this).apply {
        setTitle(title)
        setMessage(msg)
        setIcon(R.drawable.ic_logo_movie)
        setCancelable(false)

        setPositiveButton("Ok") { dialogInterface, i ->
            dialogInterface?.dismiss()
        }
    }.show()
}

fun Activity.initPermissionStorage(): Boolean {
    return if (SDK_INT >= Build.VERSION_CODES.M) {
        requestPermissions(
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ), 2
        )
        return true
    } else {
        return false
    }
}

fun convertDate(date: Int): Int {
    var bulan = 0
    bulan = when (date) {
        0 -> 1
        1 -> 2
        2 -> 3
        3 -> 4
        4 -> 5
        5 -> 6
        6 -> 7
        7 -> 8
        8 -> 9
        9 -> 10
        10 -> 11
        else -> 12
    }
    return bulan
}

fun validation(txt: String): String {
    var result = ""
    if (txt != "null" && txt != null && txt != "") {
        result = txt
    } else {
        result = "-"
    }
    return result
}

fun validation2(txt: String): String {
    var result = ""
    if (txt != "null" && txt != null) {
        result = txt
    } else {
        result = ""
    }
    return result
}

inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}
