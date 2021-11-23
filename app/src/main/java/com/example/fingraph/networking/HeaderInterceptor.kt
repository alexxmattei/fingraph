package com.example.fingraph.networking

import android.os.Build
import com.example.fingraph.utils.data.SharedPreferencesManager.Companion.domainPreferenceInstance
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request().newBuilder().addHeader("x-device-type", Build.DEVICE)
                .addHeader("Authorization", "Bearer" + domainPreferenceInstance!!.userAppData.token)
                .build()
        )
    }
}