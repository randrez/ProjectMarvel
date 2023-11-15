package com.randrez.projectmarvel.domain.models.comic

data class Comic(
    val id: Int? = null,
    val description: String? = null,
    val image: String = "",
    val thumbnail: String = "",
    val title: String = ""
)
