package com.randrez.projectmarvel.presentation.character

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.randrez.projectmarvel.data.source.ResourceResult.Error
import com.randrez.projectmarvel.data.source.ResourceResult.ErrorResource
import com.randrez.projectmarvel.data.source.ResourceResult.Loading
import com.randrez.projectmarvel.data.source.ResourceResult.Success
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

    private val _state: MutableState<CharactersListState> = mutableStateOf(CharactersListState())
    val state: State<CharactersListState> = _state
    val characters = mutableListOf<Character>()
    val isLoading = mutableStateOf(true)

    init {
        getCharacters()
    }

    private fun getCharacters() {
        val stateValue = state.value
        getCharactersUseCase.invoke().onEach { result ->
            when (result) {
                is Loading -> isLoading.value = true
                is Error -> {
                    isLoading.value = false
                    _state.value = stateValue.copy(messageError = result.message)
                }

                is ErrorResource -> {
                    isLoading.value = false
                    _state.value = stateValue.copy(messageErrorResource = result.messageResource)
                }

                is Success -> {
                    result.data?.let { list ->
                        list.forEach { character ->
                            characters.add(character)
                        }
                    }
                    isLoading.value = false
                }
            }
        }.launchIn(viewModelScope)
    }
}