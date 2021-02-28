/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
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
                navArgument(Destinations.CUTIE_CAT_DETAIL_KEY) { type = NavType.LongType }
            )
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
