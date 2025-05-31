//1번(태그로 술 추천) 화면에서 추천 버튼 클릭 시 나오는 술 이미지+정보 화면
package com.example.c2h5oh.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailScreen(tags: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("선택한 태그", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(24.dp))

        Text("🍶 추천 술 리스트 (예시)", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))

        tags.forEach { tag ->
            Text("• $tag 같은 술", fontSize = 16.sp)
        }
    }
}