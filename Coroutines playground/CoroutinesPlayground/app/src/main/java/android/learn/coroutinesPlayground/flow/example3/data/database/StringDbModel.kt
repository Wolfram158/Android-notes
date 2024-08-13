package android.learn.coroutinesPlayground.flow.example3.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "strings")
data class StringDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val str: String
)