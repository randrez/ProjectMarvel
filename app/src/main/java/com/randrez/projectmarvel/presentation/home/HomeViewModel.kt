package com.randrez.projectmarvel.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.randrez.projectmarvel.domain.useCase.GenerateMD5UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel()
class HomeViewModel @Inject constructor(
    private val generateMD5UseCase: GenerateMD5UseCase
) : ViewModel() {

    init{
        viewModelScope.launch {
            generateMD5UseCase.invoke()
        }
    }
}