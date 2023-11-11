package com.randrez.projectmarvel.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.randrez.projectmarvel.navigation.Routes.CharacterComics
import com.randrez.projectmarvel.navigation.Routes.Characters
import com.randrez.projectmarvel.navigation.Routes.Home

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Home.route) {

        composable(route = Home.route) {

        }

        composable(route = Characters.route) {

        }

        composable(route = CharacterComics.route) {

        }

        composable(route = Routes.Comic.route) {

        }
    }
}