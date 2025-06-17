package com.example.c2h5oh

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

suspend fun fetchLiquorsByTags(tags: List<String>): List<Liquor> {
    val db = FirebaseFirestore.getInstance()
    val liquors = mutableListOf<Liquor>()

    try {
        val snapshot = db.collection("liquors").get().await()
        for (doc in snapshot.documents) {
            val liquor = doc.toObject(Liquor::class.java) ?: continue

            // 조건: 태그(예: "sweet", "heavy", "~10%")가 Liquor 필드에 부합하는지 검사
            val matches = tags.all { tag ->
                when (tag) {
                    "달콤한" -> liquor.sweet
                    "상큼한" -> liquor.sour
                    "떫은" -> liquor.astringent
                    "쓴" -> liquor.bitter
                    "감칠맛이 나는" -> liquor.umami
                    "무거운" -> liquor.heavy
                    "청량한" -> liquor.sparkling
                    "~10%" -> liquor.alcohol_level <= 10
                    "10~20%" -> liquor.alcohol_level in 11..20
                    "20~30%" -> liquor.alcohol_level in 21..30
                    "30%~" -> liquor.alcohol_level > 30
                    else -> false
                }
            }

            if (matches) {
                liquors.add(liquor)
            }
        }

    } catch (e: Exception) {
        e.printStackTrace()
    }

    return liquors
}


suspend fun fetchLiquorByName(name: String): Liquor? {
    val db = FirebaseFirestore.getInstance()
    return try {
        val snapshot = db.collection("liquors")
            .whereEqualTo("name", name)
            .get()
            .await()

        snapshot.documents.firstOrNull()?.toObject(Liquor::class.java)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}