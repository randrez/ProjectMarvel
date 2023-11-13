package com.randrez.projectmarvel.data.remote.responses.comic

data class ComicDTO(
    val id: Int?,
    val description: String?,
    val images: List<Image>?,
    val thumbnailComic: ThumbnailComic?,
    val title: String?,
    val variantDescription: String?,
)