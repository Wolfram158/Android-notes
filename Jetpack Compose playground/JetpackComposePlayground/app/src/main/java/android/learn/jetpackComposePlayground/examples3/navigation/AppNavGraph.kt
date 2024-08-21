package android.learn.jetpackComposePlayground.examples3.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    itemsScreenContent: @Composable () -> Unit,
    addScreenContent: @Composable () -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Items.route
    ) {
        composable(Screen.Items.route) {
            itemsScreenContent()
        }
        composable(Screen.Add.route) {
            addScreenContent()
        }

    }
}