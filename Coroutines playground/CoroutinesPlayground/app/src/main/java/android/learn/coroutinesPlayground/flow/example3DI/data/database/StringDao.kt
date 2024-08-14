package android.learn.coroutinesPlayground.flow.example3DI.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StringDao {
    @Query("SELECT * FROM strings")
    fun getStrings(): Flow<List<StringDbModel>>

    @Query("SELECT COUNT(*) FROM strings WHERE str == :str")
    fun getCount(str: String): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertString(stringDbModel: StringDbModel)

    @Query("DELETE FROM strings WHERE id IN (SELECT id from strings WHERE str == :str LIMIT 1)")
    suspend fun deleteString(str: String)
}