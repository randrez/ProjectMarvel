package com.randrez.projectmarvel.presentation.character

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.randrez.projectmarvel.data.source.ResourceResult
import com.randrez.projectmarvel.domain.models.character.Character
import com.randrez.projectmarvel.domain.useCase.character.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    val characters = mutableListOf<Character>()
    val isLoading = mutableStateOf(true)

    init {
        getCharacters()
    }

    private fun getCharacters() {
        getCharactersUseCase.invoke().onEach { result ->
            when (result) {
                is ResourceResult.Loading -> isLoading.value = true
                is ResourceResult.Error -> {
                    isLoading.value = false
                }

                is ResourceResult.ErrorResource -> {
                    isLoading.value = false
                }

                is ResourceResult.Success -> {
                    isLoading.value = false
                    result.data?.let {list ->
                        list.forEach { character ->
                            Log.e("character", character.name ?: "")
                            characters.add(character)
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}