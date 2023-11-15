package com.randrez.projectmarvel.data.remote.responses.comic

import com.google.gson.annotations.SerializedName
import com.randrez.projectmarvel.data.remote.responses.Thumbnail

data class ComicDTO(
    val id: Int,
    val description: String?,
    val images: List<Image>?,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail?,
    val title: String,
    val variantDescription: String,
)