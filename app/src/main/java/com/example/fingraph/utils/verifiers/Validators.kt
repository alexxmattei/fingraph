package com.example.fingraph.utils.verifiers

import android.text.TextUtils
import android.util.Patterns

class Validators {
    companion object {
        fun isValidEmail(email: String?): Boolean {
            return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
        fun isValidPassword(password: String?) : Boolean {
                return !TextUtils.isEmpty(password) && (password?.length!! >= 6)
        }
        fun isSamePassword(firstPassword: String?, secondPassword: String?): Boolean {
            if(firstPassword?.length!! > 0 && secondPassword?.length!! > 0) {
                return firstPassword == secondPassword
            }
            return false
        }
    }
}