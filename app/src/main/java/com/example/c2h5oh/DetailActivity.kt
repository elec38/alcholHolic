package com.example.c2h5oh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.c2h5oh.screens.DetailScreen
import com.example.c2h5oh.theme.C2h5ohTheme

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val selectedTags = intent.getStringArrayListExtra("selected_tags") ?: arrayListOf()

        setContent {
            val navController = rememberNavController()
            C2h5ohTheme {
                var liquorList by remember { mutableStateOf<List<Liquor>>(emptyList()) }

                LaunchedEffect(true) {
                    val result = fetchLiquorsByTags(selectedTags)
                    liquorList = result
                }
                DetailScreen(
                    tags = selectedTags,
                    liquors = liquorList,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}