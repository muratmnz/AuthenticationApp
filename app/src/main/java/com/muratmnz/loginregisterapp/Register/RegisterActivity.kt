package com.muratmnz.loginregisterapp.Register

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.muratmnz.loginregisterapp.Home.HomeActivity
import com.muratmnz.loginregisterapp.Login.LoginActivity
import com.muratmnz.loginregisterapp.R

open class RegisterActivity : AppCompatActivity() {

    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var etConfPassword: EditText
    private lateinit var btnSignup: Button
    private lateinit var btnBackLogin: Button
    //lateinit var textWelcome : TextView

    private lateinit var auth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etEmail = findViewById(R.id.regEmail)
        etPassword = findViewById(R.id.regPassword)
        etConfPassword = findViewById(R.id.regConfPassword)
        btnSignup = findViewById(R.id.btnSignin)
        btnBackLogin = findViewById(R.id.btnBackLogin)
        //textWelcome = findViewById(R.id.textWelcome)

        //Initialise Firebase auth object
        auth = FirebaseAuth.getInstance()

        btnSignup.setOnClickListener {
            signUpUser()
        }
        btnBackLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun signUpUser() {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        val confirmPassword = etConfPassword.text.toString()

        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Email and password must be filled.", Toast.LENGTH_SHORT).show()
            return
        }
        if (password != confirmPassword) {
            Toast.makeText(this, "Password and Confirm password don't match.", Toast.LENGTH_SHORT)
                .show()
            return
        }
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                val intentHome = Intent(this, HomeActivity::class.java)
                //textWelcome.text = " ${email!!}"
                Toast.makeText(this, "Successfully signed up", Toast.LENGTH_SHORT).show()
                startActivity(intentHome)
                finish()
            } else {
                Toast.makeText(this, "Signed up failed.", Toast.LENGTH_SHORT).show()
            }
        }

    }
}