package com.randrez.projectmarvel.domain.models.comic

import com.google.gson.Gson
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

fun String.toByteArray(): ByteArray =
    this.toByteArray()

fun Comic.toJson(): String =
    Gson().toJson(
        Comic(
            id = this.id,
            description = this.description,
            image = this.image,
            title = this.title
        )
    )

fun String.fromJson(): Comic =
    Gson().fromJson(this, Comic::class.java)