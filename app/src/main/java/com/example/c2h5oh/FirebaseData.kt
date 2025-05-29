package com.example.c2h5oh

data class FirebaseData(
    val sampleName: String,
    val sampleNumber: Int,
    val sampleBoolean: Boolean
)


data class Liquor(
    val name: String = "",
    val alcohol: Double = 0.0,
    val description: String? = null,
    val image_url: String? = null,
    val tag: Tag? = null
)

data class Tag(
    val liquor_type: String = "",
    val price_range: Int = 0,
    val sweet: Boolean = false,
    val sour: Boolean = false,
    val astringent: Boolean = false,
    val bitter: Boolean = false,
    val umami: Boolean = false,
    val heavy: Boolean = false,
    val sparkling: Boolean = false,
    val alcohol_level: Int = 0
)
