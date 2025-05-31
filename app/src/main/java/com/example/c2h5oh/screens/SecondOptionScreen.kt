//2번 화면(술 이름 검색 시 정보 알려주는 화면)
package com.example.c2h5oh.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SecondOptionScreen() {
    var query by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "술 이름으로 검색하기", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = query,
            onValueChange = {query = it },
            label = {Text("술 이름을 입력하세요")},
            modifier = Modifier.height(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // 임시 결과
            result = when(query.trim().lowercase()) {
                "소주" -> "소주: 한국의 대표적인 증류주입니다."
                "맥주" -> "맥주: 보리를 발효시켜 만든 시원한 술입니다."
                else -> "검색 결과가 없습니다."
            }
        }) {
            Text("검색")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = result, fontSize = 18.sp)
    }
}