package com.gudhel.loginoauth.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.gudhel.loginoauth.R
import com.gudhel.loginoauth.data.ApiClient
import com.gudhel.loginoauth.data.requests.LoginRequest
import com.gudhel.loginoauth.data.responses.LoginResponse
import com.gudhel.loginoauth.utils.SessionManager
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        btnLogin.setOnClickListener {
            llProgressBar.visibility = View.VISIBLE

            val username: String = edtUsername.text.trim().toString()
            val password: String = edtPassword.text.trim().toString()

            // Validasi
            if(username.isEmpty() || password.isEmpty()) {
                llProgressBar.visibility = View.GONE
                Toast.makeText(this, "Username dan password wajib diisi!", Toast.LENGTH_LONG).show()
            } else {
                apiClient.getApiService(this).login(LoginRequest(username, password))
                    .enqueue(object : Callback<LoginResponse> {
                        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                            llProgressBar.visibility = View.GONE
                            val loginResponse = response.body()

                            if (loginResponse?.status?.code == 200 && loginResponse.data.accessToken.isNotEmpty()) {
                                sessionManager.saveAccessToken(loginResponse.data.accessToken)

                                toMain()
                            } else {
                                Toast.makeText(applicationContext, "Username dan password tidak sesuai", Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            llProgressBar.visibility = View.GONE
                            Toast.makeText(applicationContext, "Gagal kontak server", Toast.LENGTH_LONG).show()
                        }
                    })
            }
        }
    }

    fun toMain() {
        Intent(applicationContext, MainActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }
}