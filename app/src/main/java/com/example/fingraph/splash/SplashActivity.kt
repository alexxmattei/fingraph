package com.example.fingraph.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.fingraph.R
import com.example.fingraph.home.HomeActivity
import com.example.fingraph.login.LoginActivity
import com.example.fingraph.models.networking.request.VerifyTokenRequest
import com.example.fingraph.utils.data.SharedPreferencesManager

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {

    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val userCredentials = SharedPreferencesManager.getInstance(applicationContext).userAppData
        val tokenVerifier = VerifyTokenRequest(userCredentials.token)

        splashViewModel.verifyToken(tokenVerifier)
        splashViewModel.isTokenValid.observe(this) {
            if (it) {
                Handler().postDelayed({
                    val mainIntent = Intent(this, HomeActivity::class.java)
                    this.startActivity(mainIntent)
                    this.finish()
                }, 1500)
            } else {
                Handler().postDelayed({
                    val mainIntent = Intent(this, LoginActivity::class.java)
                    this.startActivity(mainIntent)
                    this.finish()
                }, 1500)
            }
        }
        splashViewModel.error.observe(this) {
            Log.i("Error at splash: ", it)
        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}