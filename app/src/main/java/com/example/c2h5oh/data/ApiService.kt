package com.example.c2h5oh.data

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call  // ← suspend를 쓸 수도 있지만, 기본형은 Call

// FastAPI의 /recommend POST 요청 정의
interface ApiService {
    @POST("/recommend")
    fun recommend(@Body input: UserInput): Call<RecommendationResponse>
}
