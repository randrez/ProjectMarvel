package com.randrez.projectmarvel.domain.models.comic

import com.randrez.projectmarvel.data.remote.responses.comic.ComicDTO

fun List<ComicDTO>.toListComic(): List<Comic> =
    this.map { it.toComic() }

fun ComicDTO.toComic() =
    Comic(
        id = this.id,
        description = this.description,
        image = if (!this.images.isNullOrEmpty()) "${this.images.first().path}.${this.images.first().extension}" else "",
        title = this.title,
        thumbnail = "${this.thumbnail?.path ?: ""}.${this.thumbnail?.extension ?: ""}"
    )