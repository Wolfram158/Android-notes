package android.learn.coroutinesPlayground.flow.example3.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [StringDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        private var instance: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "strings.db"
        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                instance?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                instance = db
                return db
            }
        }
    }

    abstract fun getStringDao(): StringDao
}