package com.example.c2h5oh

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.c2h5oh.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.example.c2h5oh.FirebaseData

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
        }


    }
    private fun setDocument(data: FirebaseData) {
        FirebaseFirestore.getInstance()
            .collection("sampleCollection")
            .document(data.sampleName)
            .set(data)
            .addOnSuccessListener {
                binding.textResult.text = "Success"
            }
            .addOnFailureListener {
                binding.textResult.text = "Failure${it.message}"
            }
    }
}