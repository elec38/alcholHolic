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


//할일  1. batch.write으로 데이터베이스 체우는 방법 코딩  2. 데이터베이스에서 꺼내오는 방법 코딩(V) 3. roomdb 바탕으로 사용자 설정 및 기록 저장 코딩


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var sampleNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setDocument(
            FirebaseData(
                sampleName = "test",
                sampleNumber = sampleNumber,
                sampleBoolean = true
            )
        )
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.buttonSet.setOnClickListener {
            sampleNumber++
            setDocument(
                FirebaseData(
                    sampleName = "test",
                    sampleNumber = sampleNumber,
                    sampleBoolean = true
                )
            )
            val liquor = Liquor(
                name = "cass",
                description = "맥주",
                image_url = "https://example.com/cass.jpg",
                liquor_type = "beer",
                price_range = 50,
                sweet = false,
                sour = false,
                astringent = false,
                bitter = true,
                umami = true,
                heavy = false,
                sparkling = false,
                alcohol_level = 10
            ).let {
                it.copy(search_tokens = generateSearchTokens(it.name))
            }

            FirebaseFirestore.getInstance()
                .collection("liquors")
                .document("${liquor.name}")
                .set(liquor)

                .addOnSuccessListener {
                    binding.textResult.text = "Success!!"
                }
                .addOnFailureListener {
                    binding.textResult.text = "Failure${it.message}"
                }
        }

        binding.buttonGetData.setOnClickListener {
            FirebaseFirestore.getInstance()
                .collection("liquors")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val liquorList = querySnapshot.toObjects(Liquor::class.java)
                    val result = liquorList.joinToString("\n") { "${it.name} (${it.alcohol_level}도)" }
                    binding.textResult.text = result
                }
                .addOnFailureListener {
                    binding.textResult.text = "불러오기 실패: ${it.message}"
                }
        }


        binding.buttonSearch.setOnClickListener {
            val searchText = binding.textSearch.text.toString()
//            ----------------------------------method1-----------------------------
            FirebaseFirestore.getInstance()
                .collection("liquors")
//                .whereEqualTo("name", searchText)
                .whereArrayContains("search_tokens", searchText)
                .get()
                .addOnSuccessListener {
                    val liquorList = it.toObjects(Liquor::class.java)
                    val result = liquorList.joinToString("") { "${it.name} (${it.alcohol_level}도)" }
                    binding.textResult.text = result

                }
                .addOnFailureListener {
                    binding.textResult.text = "불러오기 실패: ${it.message}"
                }
//            ----------------------------------method2-----------------------------
//            FirebaseFirestore.getInstance()
//                .collection("liquors")
//                .get()
//                .addOnSuccessListener { result ->
//                    for (document in result) {
//                        if ()
//                    }
//                }
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
                binding.textResult.text = "Success"
            }
            .addOnFailureListener {
                binding.textResult.text = "Failure${it.message}"
            }
    }
}