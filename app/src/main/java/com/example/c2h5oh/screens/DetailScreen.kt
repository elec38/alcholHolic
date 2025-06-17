//1번(태그로 술 추천) 화면에서 추천 버튼 클릭 시 나오는 술 이미지+정보 화면
package com.example.c2h5oh.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.c2h5oh.Liquor

@Composable
fun DetailScreen(
    tags: List<String>,
    liquors: List<Liquor>,
    onBackClick: () -> Unit,
    onApiClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            // 상단 뒤로가기 버튼
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "뒤로가기",
                        tint = Color.White
                    )
                }
            }

            Text(
                text = "🍶 추천 술 리스트",
                fontSize = 28.sp,
                color = Color(0xFFFFCBDD),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 추천 술 리스트 표시
            liquors.take(8).forEach { liquor ->
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


            Spacer(modifier = Modifier.height(90.dp))

            tags.forEach { tag ->
                Text(
                    text = "$tag 같은 술",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(50.dp)) // 버튼 위 공간 확보
        }

        // ✅ 버튼은 BoxScope 안에서 배치 (오른쪽 하단)
        Button(
            onClick = onApiClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF9C6EFF),
                contentColor = Color.White
            )
        ) {
            Text("API로 다른 술 추천 받기")
        }
    }
}
