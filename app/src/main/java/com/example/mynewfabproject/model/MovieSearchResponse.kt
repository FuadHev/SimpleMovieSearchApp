package com.example.mynewfabproject.model

data class MovieSearchResponse(
    val Response: String,
    val Search: List<Search>?,//sende ? olmayacaq
    val totalResults: String?,//sende ? olmayacaq
    val Error: String?//sen bunu yazmaa
)