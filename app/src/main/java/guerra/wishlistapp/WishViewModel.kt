package guerra.wishlistapp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import guerra.wishlistapp.data.Wish
import guerra.wishlistapp.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepository: WishRepository = Graph.wishRepository
) : ViewModel(){

    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")

    fun onWishTitleChanged(newString: String){
        wishTitleState = newString
    }

    fun onWishDescriptionChanged(newString: String){
        wishDescriptionState = newString
    }

    fun addWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.addWish(wish)
        }
    }
    fun editWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.editWish(wish)
        }
    }
    fun deleteWish(wish:Wish){
        viewModelScope.launch(Dispatchers.IO){
            wishRepository.deleteWish(wish)
        }
    }

    fun getWishById(id: Long): Flow<Wish>{
        return wishRepository.getWishById(id)
    }

    lateinit var getAllWishes: Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWishes = wishRepository.getAllWishes()
        }
    }
}
