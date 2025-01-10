package guerra.wishlistapp

import android.content.Context
import androidx.room.Room
import guerra.wishlistapp.data.WishDatabase
import guerra.wishlistapp.data.WishRepository

object Graph {
    lateinit var database: WishDatabase

    // 'by lazy' used to load the Repository just when it is needed.

    val wishRepository by lazy {
        WishRepository(wishDao = database.wishDao())
    }

    fun provide(context : Context){
        database = Room.databaseBuilder(context , WishDatabase::class.java, "wishlist.db").build()
    }
}