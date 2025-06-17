package com.example.c2h5oh.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://your-server-url" // ← 실제 서버 주소나 ngrok 주소로 교체

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)  // 예: "http://192.168.0.100:8000/"
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
