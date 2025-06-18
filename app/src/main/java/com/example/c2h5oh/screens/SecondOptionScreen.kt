//2번 화면(술 이름 검색 시 정보 알려주는 화면)
package com.example.c2h5oh.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
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
import coil.compose.AsyncImage
//import androidx.compose.ui.text.input.TextFieldValue
import com.example.c2h5oh.Liquor
import com.example.c2h5oh.fetchLiquorByName
import kotlinx.coroutines.launch


@Composable
fun SecondOptionScreen(
    onBackClick: () -> Unit // 🔥 뒤로가기 콜백 받기
) {
    var query by remember { mutableStateOf(TextFieldValue("")) }
    var result by remember { mutableStateOf("") }
    var liquorResult by remember { mutableStateOf<Liquor?>(null) }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 🔙 뒤로가기 버튼 (좌측 상단)
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
            text = "술 이름으로 검색하기",
            fontSize = 35.sp,
            color = Color(0xFFC0B0FD)
        )

        Spacer(modifier = Modifier.height(150.dp))

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = {
                Text(
                    text = "술 이름을 입력하세요",
                    fontSize = 18.sp,
                    color = Color.LightGray
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color(0xFFFFA6D1),
                unfocusedBorderColor = Color(0xFFF5C7DA),
                cursorColor = Color.White,
                focusedLabelColor = Color(0xFFC0B0FD),
                unfocusedLabelColor = Color.Gray,
                focusedContainerColor = Color.Black,
                unfocusedContainerColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                scope.launch {
                    val name = query.text.trim()
                    val liquor = fetchLiquorByName(name)
                    if (liquor != null) {
                        liquorResult = liquor
                        result = "" // 텍스트 결과는 비움
                    } else {
                        liquorResult = null
                        result = "검색 결과가 없습니다."
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFBEAFFB),
                contentColor = Color.White
            )
        ) {
            Text("검색")
        }

        Spacer(modifier = Modifier.height(30.dp))

                liquorResult?.let { liquor ->
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
                                color = Color.Gray,
                                maxLines = 2
                            )
                        }
                    }
                }
            }
        } ?: Text(
            text = result,
            fontSize = 28.sp,
            color = Color(0xFFFFD8EB)
        )
    }
}
