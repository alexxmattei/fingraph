package com.example.fingraph.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.fingraph.R

abstract class BaseActivity : AppCompatActivity() {

    private val rootView by lazy { getRootView() }

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        super.onCreate(savedInstanceState)
        setStatusBarColor()
        setNavigationBarColor()
        setBackground()
    }

    private fun setStatusBarColor() {
        val statusBarColor = resources.getColor(R.color.design_primary, null)
        window.statusBarColor = statusBarColor
    }

    private fun setNavigationBarColor() {
        val navigationBarColor = resources.getColor(R.color.design_primary, null)
        window.navigationBarColor = navigationBarColor
        WindowInsetsControllerCompat(window, rootView).isAppearanceLightNavigationBars =
            AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO
    }

    private fun setBackground() {
        val backgroundColor = resources.getColor(R.color.design_primary, null)
        getRootView().setBackgroundColor(backgroundColor)
    }
}