package com.example.c2h5oh

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.c2h5oh.screens.FirstOptionScreen
import com.example.c2h5oh.theme.C2h5ohTheme

class FirstOptionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            C2h5ohTheme {
                FirstOptionScreen(
                    onBackClick = { finish() }, // ← 뒤로가기 동작 추가
                    onRecommendClick = { selectedTags ->
                        val intent = Intent(this, DetailActivity::class.java).apply {
                            putStringArrayListExtra("selected_tags", ArrayList(selectedTags))
                        }
                        startActivity(intent)
                    }
                )
            }
        }
    }
}