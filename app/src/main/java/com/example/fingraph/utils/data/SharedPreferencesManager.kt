package com.example.fingraph.utils.data

import android.content.Context
import com.example.fingraph.models.UserData


class SharedPreferencesManager private constructor(private val domainContext: Context){
    val getLoggedInToken: Boolean
        get() {
            val sharedPreferences = domainContext.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getString("Token", null) != null
        }

    val userAppData: UserData
        get() {
            val sharedPreferences = domainContext.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
            return UserData(
                sharedPreferences.getString("Email", "") ?: "",
                sharedPreferences.getString("Password", "") ?: ""
            )
        }

    fun saveUserCredentials(user: UserData) {
        val sharedPreferences = domainContext.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("Email", user.email)

        editor.apply()
    }

    fun clearSharedPreferences() {
        val sharedPreferences = domainContext.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private const val SHARED_PREFERENCE_NAME = "mainPrefs"
        var domainPreferenceInstance: SharedPreferencesManager? = null

        @Synchronized
        fun getInstance(domainContext: Context): SharedPreferencesManager {
            if (domainPreferenceInstance == null) {
                domainPreferenceInstance = SharedPreferencesManager(domainContext)
            }
            return domainPreferenceInstance as SharedPreferencesManager
        }
    }

}