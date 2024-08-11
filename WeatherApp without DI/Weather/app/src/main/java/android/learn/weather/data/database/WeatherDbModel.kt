package android.learn.weather.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weathers")
data class WeatherDbModel(
    @PrimaryKey val code: Int,
    val name: String,
    val region: String,
    val country: String,
    val localtime: String,
    val lastUpdated: String,
    val tempC: String,
    val imageUrl: String,
    val wind: String,
    val pressure: String,
    val humidity: String,
    val cloud: String,
    val dewPoint: String
)