package guerra.wishlistapp

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(
    viewModel: WishViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    context : Context = LocalContext.current
) {
    NavHost(navController = navController, startDestination = "HomeScreen") {
        composable(Screen.HomeScreen.route) {
            HomeView(navController, viewModel, context)
        }
        composable(
            route = Screen.DetailsScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                type = NavType.LongType
                defaultValue = 0L
                nullable = false
            })
        ) { entry ->
            val id = if(entry.arguments != null) entry.arguments!!.getLong("id") else 0L
            DetailsView(id = id, viewModel = viewModel, navController = navController, context = context)
        }
    }

}