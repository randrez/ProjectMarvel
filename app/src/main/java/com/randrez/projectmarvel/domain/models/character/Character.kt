package com.randrez.projectmarvel.domain.models.character

import androidx.compose.ui.graphics.Color

data class Character(
    val id:Int,
    val name:String,
    val image:String,
    val background: Color
)
