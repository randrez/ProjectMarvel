package com.randrez.projectmarvel.data.remote

import com.randrez.projectmarvel.BuildConfig
import com.randrez.projectmarvel.data.remote.responses.character.ResponseCharacter
import com.randrez.projectmarvel.data.remote.responses.comic.ResponseComic
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MarvelApi {

    @GET("${BuildConfig.URL_VERSION_PUBLIC}characters")
    suspend fun getCharactersByName(@Query("name") name: String): ResponseCharacter

    @GET("${BuildConfig.URL_VERSION_PUBLIC}characters/{characterId}/comics")
    suspend fun getComicsByCharacterId(
        @Path("characterId") characterId: Int,
        @Query("format") format: String = "comic"
    ): ResponseComic
}