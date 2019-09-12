package pl.arturostrowski.android.mvp.app.util

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun Any.showELog(log: String) = Log.e(this::class.java.simpleName, log)

fun Activity.toast(text: String) = ToastHelper.makeToast(this, text)
fun Fragment.toast(text: String) = ToastHelper.makeToast(this.context!!, text)
