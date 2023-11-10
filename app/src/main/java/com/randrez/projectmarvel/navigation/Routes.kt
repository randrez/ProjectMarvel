package com.randrez.projectmarvel.navigation

import androidx.annotation.StringRes
import com.randrez.projectmarvel.R

sealed class Routes(
    val route: String,
    @StringRes val title: Int
) {

    object Home:Routes(
        route = ConstantsRoute.HOME,
        title = R.string.home
    )

    object Characters:Routes(
        route = ConstantsRoute.CHARACTERS,
        title = R.string.characters
    )

    object CharacterComics:Routes(
        route = ConstantsRoute.CHARACTER_COMICS,
        title = R.string.character_comics
    )

    object Comic:Routes(
        route = ConstantsRoute.COMIC,
        title = R.string.comic
    )
}
