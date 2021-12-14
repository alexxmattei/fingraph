package com.example.fingraph.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.fingraph.R
import com.example.fingraph.home.HomeActivity
import com.example.fingraph.models.networking.request.CreateUserRequest
import com.example.fingraph.utils.verifiers.Validators
import java.io.IOException

class LoginActivity : AppCompatActivity() {

    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val userEmail = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val logInButton = findViewById<Button>(R.id.logInButton)
        val rememberMe = findViewById<CheckBox>(R.id.rememberMe)
//        val forgotPasswordLink = findViewById<TextView>(R.id.forgotPassword)
//        val callToActionText = findViewById<TextView>(R.id.call)

        val sharedPreferences = getSharedPreferences("mainPrefs", Context.MODE_PRIVATE)

        if (sharedPreferences.contains("Email")) {
            rememberMe.isChecked = true
            val inputEmail = sharedPreferences.getString("Email", "")
            userEmail.setText(inputEmail)
        }

        logInButton.setOnClickListener {
            if (validateEmailAndPassword(userEmail, password)) {
                Toast.makeText(applicationContext, "Logging in", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Try again!", Toast.LENGTH_SHORT).show()
            }
        }

        rememberMe.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                saveUserData("Email", userEmail)
            } else {
                deleteUserData()
            }
        }

        loginViewModel.error.observe(this) {
            Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
        }

        loginViewModel.loginSuccess.observe(this) {
            val loginIntent = Intent(this, HomeActivity::class.java)
            this.startActivity(loginIntent)
            this.finish()
        }
    }

    private fun validateEmailAndPassword(
        inputEmailEditText: EditText,
        inputPasswordEditText: EditText
    ): Boolean {
        if (!Validators.isValidEmail(inputEmailEditText.text.toString())) {
            inputEmailEditText.error = "Invalid Email!"
            return false
        } else {
            inputEmailEditText.error = null
        }
        if (!Validators.isValidPassword(inputPasswordEditText.text.toString())) {
            inputPasswordEditText.error = "Invalid Password!"
            return false
        } else {
            inputPasswordEditText.error = null
        }
        return loginUser(inputEmailEditText.text.toString(), inputPasswordEditText.text.toString())
    }

    private fun loginUser(email: String, password: String): Boolean {
        return try {
            val createUserRequest = CreateUserRequest(email, password)
            loginViewModel.loginUser(createUserRequest)
            true
        } catch (e: Exception) {
            throw IOException("An unexpected error occurred! \nLogin process failed!")
        }
    }

    private fun saveUserData(key: String?, text: EditText) {
        val editor = getSharedPreferences("mainPrefs", MODE_PRIVATE).edit()
        editor.putString(key, text.text.toString())
        editor.apply()
    }

    private fun deleteUserData() {
        getSharedPreferences("mainPrefs", MODE_PRIVATE).edit().clear().apply()
    }
}