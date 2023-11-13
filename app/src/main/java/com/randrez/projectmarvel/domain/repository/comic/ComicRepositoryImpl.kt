package com.randrez.projectmarvel.domain.repository.comic

import com.randrez.projectmarvel.data.remote.MarvelApi
import com.randrez.projectmarvel.data.remote.responses.comic.ResponseComic
import javax.inject.Inject

class ComicRepositoryImpl @Inject constructor(private val marvelApi: MarvelApi):ComicRepository {
    override suspend fun getComicsByCharacterId(characterId: Int): ResponseComic =
        marvelApi.getComicsByCharacterId(characterId)
}