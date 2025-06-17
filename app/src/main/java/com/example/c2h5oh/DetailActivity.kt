package com.example.c2h5oh

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.example.c2h5oh.screens.DetailScreen
import com.example.c2h5oh.theme.C2h5ohTheme

class DetailActivity : ComponentActivity() {
    @SuppressLint("ContextCastToActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val selectedTags = intent.getStringArrayListExtra("selected_tags") ?: arrayListOf()

        setContent {
            val activity = LocalContext.current as? Activity
            C2h5ohTheme {
                DetailScreen(
                    tags = selectedTags,
                    onBackClick = { activity?.finish() },
                    onApiClick = {
                        val intent = Intent(activity, ApiResultActivity::class.java)
                        activity?.startActivity(intent)
                    }
                )
            }
        }
    }
}