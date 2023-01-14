package com.muratmnz.loginregisterapp.Login

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.muratmnz.loginregisterapp.Home.HomeActivity
import com.muratmnz.loginregisterapp.R
import com.muratmnz.loginregisterapp.Register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    lateinit var btnLogin: Button
    private lateinit var redirectRegister: Button //update textview

    //Firabase auth object
    lateinit var auth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //View Binding
        redirectRegister = findViewById(R.id.btnRegister)
        btnLogin = findViewById(R.id.btnLogin)
        etEmail = findViewById(R.id.email)
        etPassword = findViewById(R.id.password)

        //Initialising Firebase auth object
        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            login()
        }
        redirectRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            //using finish() to end activity
            finish()
        }
    }

    private fun login(){
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        //call signInWithEmailAndPassword
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(this,"Successfully logged in.",Toast.LENGTH_SHORT).show()
                val intentHome = Intent(this,HomeActivity::class.java)
                startActivity(intentHome)
                finish()
            }else{
                Toast.makeText(this,"Login failed",Toast.LENGTH_SHORT).show()
            }
        }
    }

}