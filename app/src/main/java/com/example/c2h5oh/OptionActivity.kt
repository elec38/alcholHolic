package com.example.c2h5oh

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.c2h5oh.screens.OptionScreen
import com.example.c2h5oh.theme.C2h5ohTheme

class OptionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            C2h5ohTheme {
                OptionScreen(
                    onTagOptionClick = {
                        // 태그 선택 화면으로 이동하는 코드
                        startActivity(Intent(this, FirstOptionActivity::class.java))
                    },
                    onSearchOptionClick = {
                        // 술 검색 화면으로 이동하는 코드
                        startActivity(Intent(this, SecondOptionActivity::class.java))
                    }
                )
            }
        }
    }
}