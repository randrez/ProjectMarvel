package com.randrez.projectmarvel.domain.models.character

import androidx.compose.ui.graphics.Color
import com.randrez.projectmarvel.data.remote.responses.character.CharacterDTO
import com.randrez.projectmarvel.ui.theme.BLUE_900
import com.randrez.projectmarvel.ui.theme.GRAY_900
import com.randrez.projectmarvel.ui.theme.GREEN_900
import com.randrez.projectmarvel.ui.theme.RED_900

fun CharacterDTO.toCharacter(): Character =
    Character(
        id = this.id,
        name = this.name,
        image = "${this.thumbnailCharacter?.path ?: ""}.${this.thumbnailCharacter?.extension ?: ""}",
        background = this.name?.toColor()
    )

fun String.toColor(): Color =
    when (this) {
        "Iron Man" -> RED_900
        "Captain America" -> BLUE_900
        "Thor" -> GRAY_900
        "Hulk" -> GREEN_900
        else -> Color.Gray
    }