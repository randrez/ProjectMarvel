package com.randrez.projectmarvel.domain.repository.character

import com.randrez.projectmarvel.data.remote.MarvelApi
import com.randrez.projectmarvel.data.remote.responses.character.ResponseCharacter
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val marvelApi: MarvelApi
) : CharacterRepository {
    override suspend fun getCharacterByName(name: String): ResponseCharacter =
        marvelApi.getCharactersByName(name)
}