package pl.arturostrowski.android.mvp.app.util

import android.content.Context
import android.widget.Toast

class ToastHelper {
    companion object {
        fun makeToast(context: Context, text: String) {
            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
        }
    }
}
