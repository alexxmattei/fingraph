package com.example.fingraph.base

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import androidx.annotation.IdRes

fun <T> lazyNotSynchronized(initializer: () -> T): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE, initializer)


fun <ViewT : View?> Activity.bindView(@IdRes idRes: Int): Lazy<ViewT> {
    return lazyNotSynchronized { findViewById(idRes) }
}

fun Activity.getRootView(): View {
    return findViewById(android.R.id.content)
}

// The if clause treats weather the device has a navigation bar at bottom
// And calculates the reals size of the device which is usable
@Suppress("DEPRECATION")
fun Context.screenWidth(): Int {
    val displayMetrics = DisplayMetrics()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        display?.getRealMetrics(displayMetrics)
    } else {
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)
    }
    return displayMetrics.widthPixels
}

@Suppress("DEPRECATION")
fun Context.screenHeight(): Int {
    val displayMetrics = DisplayMetrics()
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        display?.getRealMetrics(displayMetrics)
    } else {
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)
    }
    return displayMetrics.heightPixels
}