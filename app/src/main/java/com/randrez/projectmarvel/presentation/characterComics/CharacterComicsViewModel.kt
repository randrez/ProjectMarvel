package com.randrez.projectmarvel.presentation.characterComics

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.randrez.projectmarvel.data.source.ResourceResult.Error
import com.randrez.projectmarvel.data.source.ResourceResult.ErrorResource
import com.randrez.projectmarvel.data.source.ResourceResult.Loading
import com.randrez.projectmarvel.data.source.ResourceResult.Success
import com.randrez.projectmarvel.domain.models.comic.Comic
import com.randrez.projectmarvel.domain.useCase.comic.GetComicsByCharacterUseCase
import com.randrez.projectmarvel.tools.ConstantArguments
import com.randrez.projectmarvel.tools.ConstantArguments.CHARACTER_ID
import com.randrez.projectmarvel.tools.ConstantArguments.CHARACTER_NAME
import com.randrez.projectmarvel.tools.ConstantArguments.COLOR
import com.randrez.projectmarvel.tools.ConstantArguments.ICON
import com.randrez.projectmarvel.tools.ConstantArguments.ICON_MARVEL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterComicsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getComicsByCharacterUseCase: GetComicsByCharacterUseCase
) : ViewModel() {

    private val _state: MutableState<CharacterComicsState> = mutableStateOf(CharacterComicsState())
    val state: State<CharacterComicsState> = _state
    val comics = mutableStateListOf<Comic>()
    val isLoading = mutableStateOf(true)

    init {
        savedStateHandle.keys().forEach { key ->
            val stateValue = state.value
            when (key) {
                ICON -> savedStateHandle.get<Int>(key)?.let {
                    _state.value = stateValue.copy(icon = it)
                }

                CHARACTER_NAME -> savedStateHandle.get<String>(key)?.let {
                    _state.value = stateValue.copy(title = it)
                }

                COLOR -> savedStateHandle.get<Int>(key)?.let {
                    _state.value = stateValue.copy(color = Color(it))
                }

                ICON_MARVEL ->savedStateHandle.get<Int>(key)?.let {
                    _state.value = stateValue.copy(iconMarvel = it)
                }

                CHARACTER_ID -> savedStateHandle.get<Int>(key)?.let {
                    getCharacterComics(it)
                }
            }
        }
    }

    private fun getCharacterComics(characterId: Int) {
        val stateValue = state.value
        getComicsByCharacterUseCase.invoke(characterId).onEach { result ->
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
                        list.forEach { comic ->
                            comics.add(comic)
                        }
                    }
                    isLoading.value = false
                }
            }
        }.launchIn(viewModelScope)
    }
}