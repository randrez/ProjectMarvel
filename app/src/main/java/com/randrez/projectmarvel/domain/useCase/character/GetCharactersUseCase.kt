package com.randrez.projectmarvel.domain.useCase.character

import com.randrez.projectmarvel.R
import com.randrez.projectmarvel.data.source.ResourceResult
import com.randrez.projectmarvel.domain.models.character.Character
import com.randrez.projectmarvel.domain.models.character.ConstantsMarvelHeroNames.CAPTAIN_AMERICA
import com.randrez.projectmarvel.domain.models.character.ConstantsMarvelHeroNames.HULK
import com.randrez.projectmarvel.domain.models.character.ConstantsMarvelHeroNames.IRON_MAN
import com.randrez.projectmarvel.domain.models.character.ConstantsMarvelHeroNames.THOR
import com.randrez.projectmarvel.domain.models.character.toCharacter
import com.randrez.projectmarvel.domain.repository.character.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetCharactersUseCase constructor(
    private val characterRepository: CharacterRepository
) {
    operator fun invoke(): Flow<ResourceResult<List<Character>>> =
        flow<ResourceResult<List<Character>>> {
            emit(ResourceResult.Loading())
            val characterList = mutableListOf<Character>()

            val names = listOf(IRON_MAN, CAPTAIN_AMERICA, THOR, HULK)

            names.forEach {
                val response = characterRepository.getCharacterByName(it)
                if (response.code != 200) {
                    if (response.status.isEmpty()) {
                        emit(ResourceResult.ErrorResource(R.string.error_load_characters))
                    } else {
                        emit(ResourceResult.Error(response.status))
                    }
                    return@flow
                }
                response.data.characters?.let { remoteCharacters ->
                    characterList.add(remoteCharacters[0].toCharacter())
                }
                if (characterList.isNotEmpty() && characterList.size == 4) {
                    emit(ResourceResult.Success(characterList))
                    return@flow
                }
            }
        }.catch {
            it.printStackTrace()
            emit(ResourceResult.Error(it.message))
        }
}