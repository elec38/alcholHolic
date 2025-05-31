package com.example.c2h5oh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.c2h5oh.screens.DetailScreen
import com.example.c2h5oh.theme.C2h5ohTheme

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val selectedTags = intent.getStringArrayListExtra("selected_tags") ?: arrayListOf()

        setContent {
            C2h5ohTheme {
                DetailScreen(selectedTags)
            }
        }
    }
}