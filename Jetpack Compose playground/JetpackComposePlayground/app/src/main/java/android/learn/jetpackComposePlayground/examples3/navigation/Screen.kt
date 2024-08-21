package android.learn.jetpackComposePlayground.examples3.navigation

sealed class Screen(
    val route: String
) {
    data object Items : Screen(ROUTE_ITEMS)
    data object Add : Screen(ROUTE_ADD)

    private companion object {
        const val ROUTE_ITEMS = "items"
        const val ROUTE_ADD = "add"

    }
}