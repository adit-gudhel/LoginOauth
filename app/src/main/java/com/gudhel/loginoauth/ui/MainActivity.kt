package com.gudhel.loginoauth.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gudhel.loginoauth.R
import com.gudhel.loginoauth.utils.SessionManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sessionManager = SessionManager(this)

        val token = sessionManager.fetchAccessToken()

        // Cek token di session manager, jika tidak ada tampilkan login activity
        if(token != null) {
            tvToken.text = token
        } else {
            toLogin()
        }

        btnLogout.setOnClickListener {
            toLogin()
        }
    }

    fun toLogin() {
        sessionManager.deleteAccessToken()

        Intent(this, LoginActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }
}