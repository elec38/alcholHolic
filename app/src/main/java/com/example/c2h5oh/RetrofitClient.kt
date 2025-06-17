package com.example.c2h5oh

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.c2h5oh.data.ApiService

object RetrofitClient {
    private const val BASE_URL = "http://192.168.0.101:8000"  // 본인 FastAPI 서버 IP로 변경

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
