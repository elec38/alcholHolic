//1번 화면(태그로 술 추천받는 화면)
package com.example.c2h5oh.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FirstOptionScreen(
    onRecommendClick: (List<String>) -> Unit
) {
    val tags = listOf("달콤한", "상큼한", "쓴맛", "기분 전환", "편안한", "청량한")//임시
    val selectedTags = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("맛/기분 태그를 선택 하세요", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(20.dp))

        // 태그 리스트: 클릭 하면 선택/해제 토글됨
        Column {
            tags.forEach { tag ->
                val isSelected = tag in selectedTags
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable {
                            if (isSelected) selectedTags.remove(tag)
                            else selectedTags.add(tag)
                        },
                    elevation = if (isSelected) 8.dp else 2.dp
                ) {
                    Text(
                        text = tag,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 18.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            enabled = selectedTags.isNotEmpty(),
            onClick = {
                onRecommendClick(selectedTags)
            }
        ) {
            Text("추천 받기")
        }
    }
}