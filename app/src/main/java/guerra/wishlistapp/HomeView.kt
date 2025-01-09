package guerra.wishlistapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import guerra.wishlistapp.data.Wish

@Composable
fun HomeView(
    navController: NavHostController,
    viewModel: WishViewModel
){
    Scaffold(
        topBar = {AppBarView(title = "WishList", {})},
        floatingActionButton = {
            FloatingActionButton( //Called as FAB
                onClick = { navController.navigate(Screen.AddScreen.route)},
                modifier = Modifier.padding(all = 10.dp),
                contentColor = Color.Black,
                backgroundColor = Color.White
                )
            {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    ) {
        LazyColumn (modifier = Modifier.fillMaxSize().padding(it)){

        }
    }
}

@Composable
fun WishItem(item: Wish, onClick: () -> Unit){
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
            .clickable {
                onClick()
            },
        elevation = 10.dp,
        backgroundColor = Color.White
    ) {
        Column (modifier = Modifier.padding(16.dp)){
            Text(text = item.title, fontWeight = FontWeight.ExtraBold)
            Text(text = item.description, fontWeight = FontWeight.Thin)
        }
    }
}