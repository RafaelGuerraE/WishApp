package guerra.wishlistapp

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@Composable
fun AppBarView(
    title: String,
    onBackNavClicked: () -> Unit = {}
){
    val navigationIcon: (@Composable () -> Unit) = {
        if(!title.contains("My Wishlist")){
            IconButton(onClick = {onBackNavClicked()}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = Color.White,
                    contentDescription = ""
                )
            }
        }
    }

    TopAppBar(title = {
            Text(text = title,
                color = colorResource(id = R.color.white),
                modifier = Modifier.heightIn(max = 24.dp))
    },
        backgroundColor = Color.DarkGray,
        navigationIcon = navigationIcon
    )
}