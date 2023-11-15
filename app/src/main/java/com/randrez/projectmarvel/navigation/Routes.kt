package com.randrez.projectmarvel.navigation

sealed class Routes(
    val route: String
) {
    object Home : Routes(
        route = ConstantsRoute.HOME
    )

    object Characters : Routes(
        route = ConstantsRoute.CHARACTERS
    )

    object CharacterComics : Routes(
        route = ConstantsRoute.CHARACTER_COMICS
    )

    object Comic : Routes(
        route = ConstantsRoute.COMIC
    )
}
