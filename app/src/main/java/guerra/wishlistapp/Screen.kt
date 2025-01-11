package guerra.wishlistapp

sealed class Screen(val route: String) {
    object HomeScreen: Screen("HomeScreen")
    object DetailsScreen: Screen("DetailsScreen")
}