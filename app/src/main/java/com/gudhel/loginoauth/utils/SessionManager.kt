package com.gudhel.loginoauth.utils

import android.content.Context
import android.content.SharedPreferences
import com.gudhel.loginoauth.R

/**
 * Session manager untuk menyimpan dan mengambil session dari SharedPreferences
 */
class SessionManager(context: Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val ACCESS_TOKEN = "access_token"
    }

    /**
     * Fungsi simpan access_token
     */
    fun saveAccessToken(token: String) {
        val editor = prefs.edit()
        editor.putString(ACCESS_TOKEN, token)
            .apply()
    }

    /**
     * Fungsi get access_token
     */
    fun fetchAccessToken(): String? {
        return prefs.getString(ACCESS_TOKEN, null)
    }

    /**
     * Fungsi delete access_token / clear shared preferences
     */
    fun deleteAccessToken() {
        val editor = prefs.edit()
        editor.clear()
            .apply()
    }

}