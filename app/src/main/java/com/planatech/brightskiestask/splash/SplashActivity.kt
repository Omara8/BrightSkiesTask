package com.planatech.brightskiestask.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.planatech.brightskiestask.MainActivity
import com.planatech.brightskiestask.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setListeners()
    }

    private fun setListeners(){
        login_button?.setOnClickListener {
            val loginFragment = LoginFragment {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            loginFragment.show(supportFragmentManager, "Login")
        }
    }

}