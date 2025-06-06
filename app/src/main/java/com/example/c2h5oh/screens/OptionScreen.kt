//1,2번 옵션 선택하는 화면
package com.example.c2h5oh.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OptionScreen(
    onBackClick: () -> Unit,
    onTagOptionClick: () -> Unit,
    onSearchOptionClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "옵션을 선택하세요",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onTagOptionClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("1. 태그로 술 추천받기")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onSearchOptionClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("2. 술 이름으로 검색하기")
        }
    }
}