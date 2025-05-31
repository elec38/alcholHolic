package com.example.c2h5oh

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.c2h5oh.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.example.c2h5oh.FirebaseData
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.c2h5oh.screens.StartScreen
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.fillMaxSize
import com.example.c2h5oh.theme.C2h5ohTheme

//할일  1. batch.write으로 데이터베이스 체우는 방법 코딩  2. 데이터베이스에서 꺼내오는 방법 코딩(V) 3. roomdb 바탕으로 사용자 설정 및 기록 저장 코딩

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



    private fun setDocument(data: FirebaseData) {
        val liquor = Liquor(
            name = "jinro",
            description = "깔끔한 맛의 소주",
            image_url = "https://example.com/jinro.jpg",
            liquor_type = "소주",
            price_range = 50,
            sweet = true,
            sour = true,
            astringent = false,
            bitter = true,
            umami = true,
            heavy = true,
            sparkling = false,
            alcohol_level = 16
        ).let {
            it.copy(search_tokens = generateSearchTokens(it.name))
        }

        FirebaseFirestore.getInstance()
            .collection("liquors")
            .document("${liquor.name}")
            .set(liquor)

            .addOnSuccessListener {
//                binding.textResult.text = "Success"
            }
            .addOnFailureListener {
//                binding.textResult.text = "Failure${it.message}"
            }
    }
}