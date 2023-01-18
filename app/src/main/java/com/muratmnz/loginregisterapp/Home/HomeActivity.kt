package com.muratmnz.loginregisterapp.Home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.muratmnz.loginregisterapp.Login.LoginActivity
import com.muratmnz.loginregisterapp.R

open class HomeActivity : AppCompatActivity() {

    private lateinit var btnLogout : Button
    lateinit var emailText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //get item from other activity with intent
        var emailGet: String = intent.getStringExtra("email").toString()

        emailText = findViewById(R.id.textWelcome)
        emailText.text = "Welcome ${emailGet.split("@")[0]}"

        btnLogout = findViewById(R.id.btnLogout)

        btnLogout.setOnClickListener {
            logout()
        }

    }

    //redirect logout to login activity
    private fun logout(){
        val intentLogout = Intent(this,LoginActivity::class.java)
        startActivity(intentLogout)
        Toast.makeText(this,"Successfully log out.",Toast.LENGTH_SHORT).show()
        finish()
    }
}