package guerra.wishlistapp

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import guerra.wishlistapp.data.Wish
import kotlinx.coroutines.launch

@Composable
fun DetailsView(
    id:Long,
    viewModel: WishViewModel,
    navController: NavHostController,
    context: Context
){
    val scaffoldState = rememberScaffoldState()

    if(id != 0L){
        val wish = viewModel.getWishById(id).collectAsState(initial = Wish())
        viewModel.wishTitleState = wish.value.title
        viewModel.wishDescriptionState = wish.value.description
    }
    else{
        viewModel.wishTitleState = ""
        viewModel.wishDescriptionState = ""
    }


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { AppBarView(title =
            if(id != 0L) stringResource(id = R.string.update_wish)
            else stringResource(id = R.string.add_wish)
        ) {
            navController.navigateUp()
        }
        }
    ) {
        Column (
            modifier = Modifier.padding(it).wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.height(10.dp))

            WishTextView(
                label = "Title",
                value = viewModel.wishTitleState,
                onValueChanged = {
                    viewModel.onWishTitleChanged(it)
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            WishTextView(
                label = "Description",
                value = viewModel.wishDescriptionState,
                onValueChanged = {
                    viewModel.onWishDescriptionChanged(it)
                }
            )


            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    if(viewModel.wishTitleState.isNotEmpty() && viewModel.wishDescriptionState.isNotEmpty()){

                        if(id != 0L){
                            viewModel.editWish(wish = Wish(
                                id = id,
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            ))
                            Toast.makeText(context, "Wish has been edited.", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            viewModel.addWish(wish = Wish(
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            ))
                            Toast.makeText(context, "Wish has been created.", Toast.LENGTH_SHORT).show()
                        }

                        navController.navigateUp()
                    }
            },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)){
                Text(
                    text = if(id != 0L) stringResource(id = R.string.update_wish)
                            else stringResource(id = R.string.add_wish) ,
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(5.dp)
                )

            }
        }
    }

}

@Composable
fun WishTextView(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
){
    OutlinedTextField(
        value = value,
        onValueChange  = onValueChanged,
        label = {Text(text = label, color = Color.Black)},
        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black
        )
    )
}

@Preview(showBackground = true)
@Composable
fun Preview(){
    //WishTextView(label = "Text", "", {})
}