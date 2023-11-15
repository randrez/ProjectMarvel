package com.randrez.projectmarvel.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.randrez.projectmarvel.domain.models.comic.toByteArray
import com.randrez.projectmarvel.domain.models.comic.toJson
import com.randrez.projectmarvel.navigation.Routes.Characters
import com.randrez.projectmarvel.navigation.Routes.Home
import com.randrez.projectmarvel.presentation.character.CharacterListScreen
import com.randrez.projectmarvel.presentation.character.CharacterListViewModel
import com.randrez.projectmarvel.presentation.characterComics.CharacterComicsScreen
import com.randrez.projectmarvel.presentation.characterComics.CharacterComicsViewModel
import com.randrez.projectmarvel.presentation.comic.ComicDetailScreen
import com.randrez.projectmarvel.presentation.comic.ComicDetailViewModel
import com.randrez.projectmarvel.presentation.home.HomeScreen
import com.randrez.projectmarvel.presentation.home.HomeViewModel
import com.randrez.projectmarvel.tools.ConstantArguments.CHARACTER_ID
import com.randrez.projectmarvel.tools.ConstantArguments.CHARACTER_NAME
import com.randrez.projectmarvel.tools.ConstantArguments.COLOR
import com.randrez.projectmarvel.tools.ConstantArguments.COMIC
import com.randrez.projectmarvel.tools.ConstantArguments.ICON
import com.randrez.projectmarvel.tools.ConstantArguments.ICON_MARVEL

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Home.route) {

        composable(route = Home.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen {
                navController.navigate(Characters.route)
            }
        }

        composable(route = Characters.route) {
            val viewModel: CharacterListViewModel = hiltViewModel()
            CharacterListScreen(
                state = viewModel.state.value,
                characters = viewModel.characters,
                loading = viewModel.isLoading.value,
                onSelectCharacter = {
                    navController.navigate(
                        "${Routes.CharacterComics.route}/${it.icon}/${it.id}/${it.name}/${it.background.toArgb()}/${it.iconMarvel}"
                    )
                }
            ) {
                navController.popBackStack(route = Routes.Home.route, inclusive = false)
            }
        }

        val argumentsCharacterComics = listOf(
            navArgument(ICON) {
                type = NavType.IntType
            },
            navArgument(CHARACTER_ID) {
                type = NavType.IntType
            },
            navArgument(CHARACTER_NAME) {
                type = NavType.StringType
            },
            navArgument(COLOR) {
                type = NavType.IntType
            },
            navArgument(ICON_MARVEL) {
                type = NavType.IntType
            }
        )

        composable(
            route = "${Routes.CharacterComics.route}/{icon}/{characterId}/{characterName}/{color}/{iconMarvel}",
            arguments = argumentsCharacterComics
        ) {
            val viewModel: CharacterComicsViewModel = hiltViewModel()
            val state = viewModel.state.value
            CharacterComicsScreen(
                state = state,
                comics = viewModel.comics,
                loading = viewModel.isLoading.value,
                onSelectComic = {
                    navController.navigate(
                        "${Routes.Comic.route}/${it.toJson().toByteArray().toString()}/${state.color}/${state.iconMarvel}"
                    )
                }
            ) {
                navController.popBackStack()
            }
        }

        val argumentsComic = listOf(
            navArgument(COMIC) {
                type = NavType.StringType
            },
            navArgument(COLOR) {
                type = NavType.IntType
            },
            navArgument(ICON_MARVEL) {
                type = NavType.IntType
            }
        )

        composable(
            route = "${Routes.Comic.route}/{comic}/{color}/{iconMarvel}",
            arguments = argumentsComic
        ) {
            val viewModel: ComicDetailViewModel = hiltViewModel()
            ComicDetailScreen(state = viewModel.state.value) {
                navController.popBackStack()
            }
        }
    }
}