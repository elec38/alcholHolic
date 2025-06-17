package com.example.c2h5oh.network

import com.example.c2h5oh.Liquor
import com.example.c2h5oh.util.parseLiquorFromCode
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.call.*
import io.ktor.http.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

@Serializable
data class UserInput(
    val taste: String,
    val price: Int,
    val heavy: Boolean,
    val sparkling: Boolean,
    val alcohol_level: Int
)

@Serializable
data class RecommendationResponse(val recommendation: String)

suspend fun fetchRecommendationFromApi(tags: List<String>): Liquor {
    val taste = when {
        "달콤한" in tags -> "sweet"
        "상큼한" in tags -> "sour"
        "떫은" in tags -> "astringent"
        "쓴" in tags -> "bitter"
        "감칠맛이 나는" in tags -> "umami"
        else -> "sweet"
    }

    val heavy = "무거운" in tags
    val sparkling = "청량한" in tags

    val alcohol_level = when {
        tags.any { it.contains("~10%") } -> 1
        tags.any { it.contains("10~20%") } -> 2
        tags.any { it.contains("20~30%") } -> 3
        tags.any { it.contains("30%~") } -> 4
        else -> 1
    }

    val price = 3

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    return try {
        val response: HttpResponse = client.post("http://10.0.2.2:8000/recommend") {
            contentType(ContentType.Application.Json)
            setBody(UserInput(taste, price, heavy, sparkling, alcohol_level))
        }

        val body = response.bodyAsText()
        val recommendation = Json.decodeFromString<RecommendationResponse>(body).recommendation

        val rawCode = recommendation
            .substringAfter("val liquor = ")
            .substringBefore(".let")
            .replace("```kotlin", "")
            .replace("```", "")
            .trim()

        parseLiquorFromCode(rawCode)
    } finally {
        client.close()
    }
}
