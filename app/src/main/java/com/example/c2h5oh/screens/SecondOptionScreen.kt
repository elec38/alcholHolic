//2번 화면(술 이름 검색 시 정보 알려주는 화면)
package com.example.c2h5oh.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SecondOptionScreen() {
    var query by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "술 이름으로 검색하기",
            fontSize = 35.sp,
            color = Color(0xFFC0B0FD)
            )

        Spacer(modifier = Modifier.height(150.dp))

        var query by remember { mutableStateOf(TextFieldValue("")) }

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = {
                Text(
                    text = "술 이름을 입력하세요",
                    fontSize = 10.sp,
                    color = Color.LightGray // 여기에서 글자색만 지정
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,             // 입력 중 텍스트 색
                unfocusedTextColor = Color.White,           // 포커스 해제 시 텍스트 색
                focusedBorderColor = Color(0xFFFFA6D1),     // 포커스 상태 테두리
                unfocusedBorderColor = Color(0xFFF5C7DA),   // 비포커스 테두리
                cursorColor = Color.White,                  // 커서 색
                focusedLabelColor = Color(0xFFC0B0FD),
                unfocusedLabelColor = Color.Gray,
                focusedContainerColor = Color.Black,     // 배경색
                unfocusedContainerColor = Color.Black
            )

        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = {
            // 임시 결과
                result = when(query.text.trim().lowercase()) {
                    "소주" -> "소주: 한국의 대표적인 증류주입니다."
                    "맥주" -> "맥주: 보리를 발효시켜 만든 시원한 술입니다."
                    else -> "검색 결과가 없습니다."
                }
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFBEAFFB),  // 버튼 내부 배경색
                contentColor = Color.White            // 버튼 안 글자색
            )
        ) {
            Text("검색")
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = result,
            fontSize = 28.sp,
            color = Color(0xFFFFD8EB)
        )
    }
}
