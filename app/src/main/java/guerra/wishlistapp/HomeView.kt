package guerra.wishlistapp

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import guerra.wishlistapp.data.Wish

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(
    navController: NavHostController,
    viewModel: WishViewModel,
    context: Context
) {
    Scaffold(
        topBar = { AppBarView(title = "WishList", {}) },
        floatingActionButton = {
            FloatingActionButton( //Called as FAB
                onClick =
                {
                    navController.navigate(Screen.DetailsScreen.route + "/0L")
                },
                modifier = Modifier.padding(all = 10.dp),
                contentColor = Color.Black,
                backgroundColor = Color.White
            )
            {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    ) {

        val wishList = viewModel.getAllWishes.collectAsState(initial = listOf())

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(wishList.value, key = { wish-> wish.id })
            { wish ->

                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                            viewModel.deleteWish(wish)
                            Toast.makeText(context,"Wish successfully deleted", Toast.LENGTH_SHORT).show()
                        }
                        true
                    }
                )

                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    dismissThresholds = { FractionalThreshold(0.40f) },
                    dismissContent = {
                        WishItem(item = wish) {
                            val id = wish.id
                            navController.navigate(Screen.DetailsScreen.route + "/$id")
                        }
                    },
                    background = {
                        Box(modifier = Modifier.fillMaxSize()
                            .background(Color.White)
                            .padding(horizontal = 20.dp),
                            contentAlignment = Alignment.CenterEnd){
                            Icon(imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = Color.Red)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun WishItem(item: Wish, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
            .clickable {
                onClick()
            },
        elevation = 10.dp,
        backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = item.title, fontWeight = FontWeight.ExtraBold)
            Text(text = item.description, fontWeight = FontWeight.Thin)
        }
    }
}