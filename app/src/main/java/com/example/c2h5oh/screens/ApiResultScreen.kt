package com.example.c2h5oh.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.c2h5oh.Liquor
import com.example.c2h5oh.network.fetchRecommendationFromApi
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun ApiResultScreen(selectedTags: List<String>) {
    var liquor by remember { mutableStateOf<Liquor?>(null) }
    var errorMsg by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            val result = fetchRecommendationFromApi(selectedTags)
            Log.d("API_RESULT", "추천 받은 술: ${result.name}") // ✅ 여기!
            liquor = result
        } catch (e: Exception) {
            Log.e("API_ERROR", "에러 발생: ${e.message}", e)
            errorMsg = e.message
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .align(Alignment.TopCenter)
        ) {
            Text("🍸 추천 결과", fontSize = 24.sp, color = Color.White)
            Spacer(modifier = Modifier.height(24.dp))

            when {
                liquor != null -> {
                    Text(
                        text = "🍶 AI 추천 결과",
                        fontSize = 28.sp,
                        color = Color(0xFFFFCBDD),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LiquorCard(liquor!!)
                }

                errorMsg != null -> {
                    Text("오류 발생: $errorMsg", color = Color.Red)
                }

                else -> {
                    Text("불러오는 중...", color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun LiquorCard(liquor: Liquor) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = liquor.image_url,
                contentDescription = liquor.name,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Color.White, RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = liquor.name,
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "${liquor.alcohol_level}% alcohol",
                    fontSize = 14.sp,
                    color = Color.LightGray
                )
                liquor.description?.let {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = it,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}