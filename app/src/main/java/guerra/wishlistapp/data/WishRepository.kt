package guerra.wishlistapp.data

import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao: WishDao) {

    suspend fun addWish(wish: Wish){
        wishDao.addWish(wish)
    }

    suspend fun editWish(wish: Wish){
        wishDao.editWish(wish)
    }

    suspend fun deleteWish(wish: Wish){
        wishDao.deleteWish(wish)
    }

    fun getAllWishes() : Flow<List<Wish>> = wishDao.getAllWishes()

    fun getWishById(id: Long) : Flow<Wish> = wishDao.getWishById(id)

}