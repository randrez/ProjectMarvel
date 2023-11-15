package com.randrez.projectmarvel.presentation.comic

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.randrez.projectmarvel.R
import com.randrez.projectmarvel.domain.models.comic.Comic
import com.randrez.projectmarvel.ui.theme.RED_A700

data class ComicDetailState(
    val color: Color = RED_A700,
    @DrawableRes val iconMarvel: Int = R.drawable.ic_marvel_red,
    val comic: Comic = Comic()
)
