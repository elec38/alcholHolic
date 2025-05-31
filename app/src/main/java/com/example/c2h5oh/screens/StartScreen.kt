//홈화면(시작 화면)
package com.example.c2h5oh.screens

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.c2h5oh.OptionActivity


@Composable
fun StartScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "WELCOME",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF7FB9E1),
            letterSpacing = 2.sp
        )
        Text(
            text = "to",
            fontSize = 35.sp,
            fontStyle = FontStyle.Italic,
            color = Color(0xFFFFFFFF)
        )
        Text(
            text = "올술옳술",
            fontSize = 65.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFFB18FE7),
            fontFamily = FontFamily.Cursive
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { context.startActivity(Intent(context, OptionActivity::class.java)) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFA3BD), // 버튼 배경
                contentColor = Color.White          // 텍스트 색
            )
        ) {
            Text("시작하기")
        }
    }
}