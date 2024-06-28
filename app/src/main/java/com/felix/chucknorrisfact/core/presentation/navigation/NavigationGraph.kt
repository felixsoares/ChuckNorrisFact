package com.felix.chucknorrisfact.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.felix.chucknorrisfact.ui.favorite_feature.presentation.FavoritesScreen
import com.felix.chucknorrisfact.ui.main_feature.presentation.MainFeatureScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
) {

    NavHost(navController = navController, startDestination = NavigationPath.HOME.route) {
        composable(NavigationPath.HOME.route) {
            MainFeatureScreen(
                navigateToFavorites = { navController.navigate(NavigationPath.FAVORITES.route) }
            )
        }

        composable(NavigationPath.FAVORITES.route) {
            FavoritesScreen(
                onClickBack = { navController.navigateUp() }
            )
        }
    }
}