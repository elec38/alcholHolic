package com.example.c2h5oh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.c2h5oh.screens.StartScreen
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.fillMaxSize
import com.example.c2h5oh.theme.C2h5ohTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            C2h5ohTheme { // ← 여기에 감싸야 적용됨!!
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background // 이제 이게 검정색!
                ) {
                    StartScreen()
                }
            }// 👉 아래 Composable 함수 호출
        }
    }
}