package com.randrez.projectmarvel.domain.models.character

import androidx.compose.ui.graphics.Color
import com.randrez.projectmarvel.R
import com.randrez.projectmarvel.data.remote.responses.character.CharacterDTO
import com.randrez.projectmarvel.domain.models.character.ConstantsMarvelHeroNames.CAPTAIN_AMERICA
import com.randrez.projectmarvel.domain.models.character.ConstantsMarvelHeroNames.HULK
import com.randrez.projectmarvel.domain.models.character.ConstantsMarvelHeroNames.IRON_MAN
import com.randrez.projectmarvel.domain.models.character.ConstantsMarvelHeroNames.THOR
import com.randrez.projectmarvel.ui.theme.BLUE_900
import com.randrez.projectmarvel.ui.theme.GRAY_900
import com.randrez.projectmarvel.ui.theme.GREEN_900
import com.randrez.projectmarvel.ui.theme.RED_900

fun CharacterDTO.toCharacter(): Character =
    Character(
        id = this.id,
        name = this.name,
        image = "${this.thumbnail?.path ?: ""}.${this.thumbnail?.extension ?: ""}",
        background = this.name.toColor(),
        icon = this.name.toIconCharacter(),
        iconMarvel = this.name.toIconMarvelColor()
    )

fun String.toColor(): Color =
    when (this) {
        IRON_MAN -> RED_900
        CAPTAIN_AMERICA -> BLUE_900
        THOR -> GRAY_900
        HULK -> GREEN_900
        else -> Color.Gray
    }

fun String.toIconCharacter(): Int =
    when (this) {
        IRON_MAN -> R.drawable.iron_man
        CAPTAIN_AMERICA -> R.drawable.captain_america
        THOR -> R.drawable.thor
        HULK -> R.drawable.hulk
        else -> R.drawable.marvel
    }

fun String.toIconMarvelColor(): Int =
    when (this) {
        IRON_MAN -> R.drawable.ic_marvel_red
        CAPTAIN_AMERICA -> R.drawable.ic_marvel_blue
        THOR -> R.drawable.ic_marvel_black
        HULK -> R.drawable.ic_marvel_green
        else -> R.drawable.ic_marvel_red
    }