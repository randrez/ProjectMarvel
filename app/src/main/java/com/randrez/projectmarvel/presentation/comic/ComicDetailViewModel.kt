package com.randrez.projectmarvel.presentation.comic

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.randrez.projectmarvel.domain.models.comic.fromJson
import com.randrez.projectmarvel.domain.models.comic.toByteArray
import com.randrez.projectmarvel.tools.ConstantArguments
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicDetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _state: MutableState<ComicDetailState> = mutableStateOf(ComicDetailState())
    val state: State<ComicDetailState> = _state
    init {
        savedStateHandle.keys().forEach { key ->
            val stateValue = state.value
            when (key) {
                ConstantArguments.COMIC -> savedStateHandle.get<String>(key)?.let {
                    val json = String(it.toByteArray())
                    val comic = json.fromJson()
                    _state.value = stateValue.copy(comic = comic)
                }

                ConstantArguments.COLOR -> savedStateHandle.get<Int>(key)?.let {
                    _state.value = stateValue.copy(color = Color(it))
                }

                ConstantArguments.ICON_MARVEL ->savedStateHandle.get<Int>(key)?.let {
                    _state.value = stateValue.copy(iconMarvel = it)
                }
            }
        }
    }
}