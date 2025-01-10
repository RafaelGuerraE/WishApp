package guerra.wishlistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao {

    //Using suspend key-word to be async

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addWish(wish: Wish)

    @Update
    abstract suspend fun editWish(wish: Wish)

    @Delete
    abstract suspend fun deleteWish(wish: Wish)

    //Functions using the Flow data type (it already is a asynchronous data)

    @Query("SELECT * FROM `wishes`")
    abstract fun getAllWishes(): Flow<List<Wish>>

    @Query("SELECT * FROM `wishes` WHERE id + :id")
    abstract fun getWishById(id:Long): Flow<Wish>

}