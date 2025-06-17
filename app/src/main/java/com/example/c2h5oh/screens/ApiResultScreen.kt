package com.example.c2h5oh.screens


import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.c2h5oh.data.UserInput
import com.example.c2h5oh.data.RetrofitClient

@Composable
fun ApiResultScreen() {
    var result by remember { mutableStateOf("추천 결과를 불러오는 중...") }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            // 예: suspend 함수 호출
            result = fetchRecommendationFromApi()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = result, color = Color.White, fontSize = 20.sp)
    }
}

suspend fun fetchRecommendationFromApi(): String {
    return try {
        val input = UserInput(
            taste = "sweet",           // 또는 intent로 값 전달 가능
            price = 3,
            heavy = false,
            sparkling = true,
            alcohol_level = 2
        )

        val response = RetrofitClient.apiService.recommend(input).execute()
        if (response.isSuccessful) {
            response.body()?.recommendation ?: "추천 결과가 비어있습니다."
        } else {
            "서버 오류: ${response.code()}"
        }
    } catch (e: Exception) {
        "API 호출 실패: ${e.message}"
    }
}