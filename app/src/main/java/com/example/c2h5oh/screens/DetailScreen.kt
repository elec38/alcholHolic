//1번(태그로 술 추천) 화면에서 추천 버튼 클릭 시 나오는 술 이미지+정보 화면
package com.example.c2h5oh.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailScreen(
    tags: List<String>,
    onBackClick: () -> Unit // 이전 화면으로 가는 콜백 추가
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "뒤로가기",
                    tint = Color.White
                )
            }
        }

        Text(
            text = "선택한 태그",
            fontSize = 40.sp,
            color = Color(0xFFC0B0FD),

        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "🍶 추천 술 리스트 (예시)",
            fontSize = 35.sp,
            color = Color(0xFFFFCBDD)
        )

        Spacer(modifier = Modifier.height(8.dp))

        tags.forEach { tag ->
            Text(
                text = "• $tag 같은 술",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}