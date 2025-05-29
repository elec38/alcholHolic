package com.example.c2h5oh

data class FirebaseData(
    val sampleName: String,
    val sampleNumber: Int,
    val sampleBoolean: Boolean
)


data class Liquor(
    val name: String = "",
    val description: String? = null,
    val image_url: String? = null,
    val liquor_type: String = "",
    val price_range: Int = 0,
    val sweet: Boolean = false,
    val sour: Boolean = false,
    val astringent: Boolean = false,
    val bitter: Boolean = false,
    val umami: Boolean = false,
    val heavy: Boolean = false,
    val sparkling: Boolean = false,
    val alcohol_level: Int = 0,
    val search_tokens: List<String> = emptyList()
)

fun generateSearchTokens(input: String): List<String> {
    val tokens = mutableListOf<String>()
    for (i in 1..input.length) {
        tokens.add(input.substring(0, i).lowercase())
    }
    return tokens
}
