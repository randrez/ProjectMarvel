package com.randrez.projectmarvel.domain.repository.character

import com.randrez.projectmarvel.data.remote.responses.character.ResponseCharacter

interface CharacterRepository {
    suspend fun getCharacterByName(name:String):ResponseCharacter
}