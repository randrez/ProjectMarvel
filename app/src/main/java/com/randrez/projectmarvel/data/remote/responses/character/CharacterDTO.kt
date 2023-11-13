package com.randrez.projectmarvel.data.remote.responses.character

import com.google.gson.annotations.SerializedName

data class CharacterDTO(
    val id: Int?,
    val modified: String?,
    val name: String?,
    val description: String?,
    val resourceURI: String?,
    @SerializedName("thumbnail")
    val thumbnailCharacter: ThumbnailCharacter?
)