package com.randrez.projectmarvel.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.randrez.projectmarvel.R
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
import com.randrez.projectmarvel.tools.ConstantArguments.COLOR
import com.randrez.projectmarvel.tools.ConstantArguments.COMIC_DESCRIPTION
import com.randrez.projectmarvel.tools.ConstantArguments.COMIC_IMAGE
import com.randrez.projectmarvel.tools.ConstantArguments.COMIC_TITLE
import com.randrez.projectmarvel.tools.ConstantArguments.ICON
import com.randrez.projectmarvel.tools.ConstantArguments.ICON_MARVEL
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

@RequiresApi(Build.VERSION_CODES.O)
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
                        route = "${Routes.CharacterComics.route}/${it.icon}/${it.id}/${it.background.toArgb()}/${it.iconMarvel}"
                    )
                }
            ) {
                navController.popBackStack(route = Routes.Home.route, inclusive = false)
            }
        }

        composable(
            route = "${Routes.CharacterComics.route}/{icon}/{characterId}/{color}/{iconMarvel}",
            arguments = listOf(
                navArgument(ICON) {
                    type = NavType.IntType
                },
                navArgument(CHARACTER_ID) {
                    type = NavType.IntType
                },
                navArgument(COLOR) {
                    type = NavType.IntType
                },
                navArgument(ICON_MARVEL) {
                    type = NavType.IntType
                }
            )
        ) {
            val viewModel: CharacterComicsViewModel = hiltViewModel()
            val state = viewModel.state.value
            val notFoundDescription = stringResource(id = R.string.no_description)
            CharacterComicsScreen(
                state = state,
                comics = viewModel.comics,
                loading = viewModel.isLoading.value,
                onSelectComic = { comic ->
                    val encodedUrl = encodeImage(comic.image)
                    val comicDescription = comic.description?.trimIndent() ?: notFoundDescription
                    navController.navigate(route = "${Routes.Comic.route}/${comic.title}/${comicDescription}/${state.color.toArgb()}/${encodedUrl}")
                }
            ) {
                navController.popBackStack()
            }
        }

        composable(
            route = "${Routes.Comic.route}/{comicTitle}/{comicDescription}/{color}/{comicImage}",
            arguments = listOf(
                navArgument(COMIC_TITLE) {
                    type = NavType.StringType
                },
                navArgument(COMIC_DESCRIPTION) {
                    type = NavType.StringType
                },
                navArgument(COLOR) {
                    type = NavType.IntType
                },
                navArgument(COMIC_IMAGE) {
                    type = NavType.StringType
                }
            )
        ) {
            val viewModel: ComicDetailViewModel = hiltViewModel()
            ComicDetailScreen(state = viewModel.state.value) {
                viewModel.isBackPressed()
                navController.popBackStack()
            }
        }
    }
}

fun encodeImage(image: String): String? =
    if (image.isNotEmpty()) {
        try {
            URLEncoder.encode(image, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
            null
        }
    } else {
        null
    }