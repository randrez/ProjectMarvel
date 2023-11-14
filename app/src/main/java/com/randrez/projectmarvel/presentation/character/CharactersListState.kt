package com.randrez.projectmarvel.presentation.character

import androidx.annotation.StringRes

data class CharactersListState(
    val messageError:String? = null,
    @StringRes val messageErrorResource:Int? = null
)
