package com.muratmnz.loginregisterapp.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageView
import com.muratmnz.loginregisterapp.R

class SplashActivity : AppCompatActivity() {

    lateinit var loginLogo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //hide status bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        loginLogo = findViewById(R.id.animationView)

        //animate logo and navigate next activity
        loginLogo.alpha = 0f
        loginLogo.animate().setDuration(1500).alpha(1f).withEndAction {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}