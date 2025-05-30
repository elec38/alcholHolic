package com.example.c2h5oh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.c2h5oh.screens.SecondOptionScreen
import com.example.c2h5oh.theme.C2h5ohTheme

class SecondOptionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            C2h5ohTheme {
                SecondOptionScreen()
            }
        }
    }
}