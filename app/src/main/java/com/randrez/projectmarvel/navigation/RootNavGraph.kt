package com.randrez.projectmarvel.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.randrez.projectmarvel.navigation.Routes.CharacterComics
import com.randrez.projectmarvel.navigation.Routes.Characters
import com.randrez.projectmarvel.navigation.Routes.Home
import com.randrez.projectmarvel.presentation.character.CharacterListScreen
import com.randrez.projectmarvel.presentation.character.CharacterListViewModel
import com.randrez.projectmarvel.presentation.home.HomeScreen
import com.randrez.projectmarvel.presentation.home.HomeViewModel

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Home.route) {

        composable(route = Home.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen {
                viewModel.generateMD5()
                navController.navigate(Characters.route)
            }
        }

        composable(route = Characters.route) {
            val viewModel: CharacterListViewModel = hiltViewModel()
            CharacterListScreen(
                characters = viewModel.characters,
                loading = viewModel.isLoading.value
            ) {
                navController.popBackStack(route = Routes.Home.route, inclusive = false)
            }
        }

        composable(route = CharacterComics.route) {

        }

        composable(route = Routes.Comic.route) {

        }
    }
}