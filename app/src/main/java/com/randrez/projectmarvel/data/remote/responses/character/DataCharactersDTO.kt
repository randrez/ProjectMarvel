package com.randrez.projectmarvel.data.remote.responses.character

import com.google.gson.annotations.SerializedName

data class DataCharactersDTO(
    val count: Int?,
    val limit: Int?,
    val offset: Int?,
    @SerializedName("results")
    val characters: List<CharacterDTO>?,
    val total: Int?
)