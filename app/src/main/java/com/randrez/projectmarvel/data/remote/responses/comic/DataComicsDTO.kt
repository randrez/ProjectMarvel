package com.randrez.projectmarvel.data.remote.responses.comic

import com.randrez.projectmarvel.data.remote.responses.character.CharacterDTO

data class DataComicsDTO(
    val count: Int?,
    val limit: Int?,
    val offset: Int?,
    val comics: List<ComicDTO>?,
    val total: Int?
)
