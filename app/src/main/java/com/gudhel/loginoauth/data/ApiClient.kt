package com.gudhel.loginoauth.data

import android.content.Context
import com.gudhel.loginoauth.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private lateinit var apiService: ApiService

    fun getApiService(context: Context) : ApiService {
        // Inisialisasi Retrofit jika belum di inisialisasi
        if (!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient(context))
                .build()

            apiService = retrofit.create(ApiService::class.java)
        }

        return apiService
    }

    /**
     * Inisilisasi OkhttpClient dengan interceptor
     */
    private fun okhttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor(context))
            .build()
    }

}