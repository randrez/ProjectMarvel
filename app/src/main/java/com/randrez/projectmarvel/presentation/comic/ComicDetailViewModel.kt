package com.randrez.projectmarvel.presentation.comic

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.randrez.projectmarvel.tools.ConstantArguments.COLOR
import com.randrez.projectmarvel.tools.ConstantArguments.COMIC_DESCRIPTION
import com.randrez.projectmarvel.tools.ConstantArguments.COMIC_IMAGE
import com.randrez.projectmarvel.tools.ConstantArguments.COMIC_TITLE
import dagger.hilt.android.lifecycle.HiltViewModel
import java.net.URLDecoder
import javax.inject.Inject

@HiltViewModel
class ComicDetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _state: MutableState<ComicDetailState> = mutableStateOf(ComicDetailState())
    val state: State<ComicDetailState> = _state

    init {
        savedStateHandle.keys().forEach { key ->
            val stateValue = state.value
            when (key) {
                COMIC_TITLE -> savedStateHandle.get<String>(key)?.let {
                    _state.value = stateValue.copy(title = it)
                }

                COMIC_DESCRIPTION -> savedStateHandle.get<String>(key)?.let {
                    _state.value = stateValue.copy(description = it)
                }

                COLOR -> savedStateHandle.get<Int>(key)?.let {
                    _state.value = stateValue.copy(color = Color(it))
                }

                COMIC_IMAGE -> savedStateHandle.get<String>(key)?.let {
                    if (it.isNotBlank() && it != "null") {
                        val decodedUrl = URLDecoder.decode(it, "UTF-8")
                        _state.value = stateValue.copy(image = decodedUrl)
                    }
                }
            }
        }
    }

    fun isBackPressed() {
        _state.value = state.value.copy(isBackPressed = true)
    }
}