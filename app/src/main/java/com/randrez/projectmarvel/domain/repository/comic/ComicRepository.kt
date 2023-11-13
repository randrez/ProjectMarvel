package com.randrez.projectmarvel.domain.repository.comic

import com.randrez.projectmarvel.data.remote.responses.comic.ResponseComic

interface ComicRepository {

    suspend fun getComicsByCharacterId(characterId:Int):ResponseComic
}