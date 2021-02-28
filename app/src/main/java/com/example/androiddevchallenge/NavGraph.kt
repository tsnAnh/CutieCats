package com.example.androiddevchallenge

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.androiddevchallenge.model.CutieCat
import com.example.androiddevchallenge.model.cats
import com.example.androiddevchallenge.ui.detail.Detail
import com.example.androiddevchallenge.ui.overview.Overview

object Destinations {
    const val OVERVIEW_ROUTE = "overview"
    const val DETAILS_ROUTE = "details"
    const val CUTIE_CAT_DETAIL_KEY = "catId"
}

@Composable
fun NavGraph(startDestination: String = Destinations.OVERVIEW_ROUTE) {
    val navController = rememberNavController()

    val actions = remember(navController) { Actions(navController) }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Destinations.OVERVIEW_ROUTE) {
            Overview(actions.selectCat)
        }
        composable(
            "${Destinations.DETAILS_ROUTE}/{${Destinations.CUTIE_CAT_DETAIL_KEY}}",
            arguments = listOf(
                navArgument(Destinations.CUTIE_CAT_DETAIL_KEY) { type = NavType.LongType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            Detail(
                cutieCat = cats.first { it.id == arguments.getLong(Destinations.CUTIE_CAT_DETAIL_KEY) },
                navigateUp = actions.navigateUp
            )
        }
    }
}

class Actions(navController: NavHostController) {
    val selectCat: (CutieCat) -> Unit = {
        navController.navigate("${Destinations.DETAILS_ROUTE}/${it.id}")
    }
    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }
}
