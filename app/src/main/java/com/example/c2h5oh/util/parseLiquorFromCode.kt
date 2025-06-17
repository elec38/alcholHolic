package com.example.c2h5oh.util
import com.example.c2h5oh.Liquor


fun parseLiquorFromCode(code: String): Liquor {
    fun extract(field: String): String =
        "$field = \"([^\"]+)\"".toRegex().find(code)?.groupValues?.get(1) ?: ""

    fun extractBool(field: String): Boolean =
        "$field = (true|false)".toRegex().find(code)?.groupValues?.get(1)?.toBooleanStrictOrNull() ?: false

    fun extractInt(field: String): Int =
        "$field = (\\d+)".toRegex().find(code)?.groupValues?.get(1)?.toIntOrNull() ?: 0

    return Liquor(
        name = extract("name"),
        description = extract("description"),
        image_url = extract("image_url"),
        liquor_type = extract("liquor_type"),
        price_range = extractInt("price_range"),
        sweet = extractBool("sweet"),
        sour = extractBool("sour"),
        astringent = extractBool("astringent"),
        bitter = extractBool("bitter"),
        umami = extractBool("umami"),
        heavy = extractBool("heavy"),
        sparkling = extractBool("sparkling"),
        alcohol_level = extractInt("alcohol_level"),
        search_tokens = listOf() // 필요시 generateSearchTokens 호출
    )
}
