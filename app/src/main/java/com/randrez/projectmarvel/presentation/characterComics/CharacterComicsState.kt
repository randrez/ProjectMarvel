package com.randrez.projectmarvel.presentation.characterComics

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.randrez.projectmarvel.R
import com.randrez.projectmarvel.ui.theme.GRAY_900

data class CharacterComicsState(
    val messageError: String? = null,
    @StringRes val messageErrorResource: Int? = null,
    @DrawableRes val icon: Int = R.drawable.marvel,
    val title: String = "",
    val color: Color = GRAY_900,
    @DrawableRes val iconMarvel: Int = R.drawable.ic_marvel_red
)
