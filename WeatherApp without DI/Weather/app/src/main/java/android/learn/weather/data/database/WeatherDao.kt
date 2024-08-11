package android.learn.weather.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weathers")
    fun getWeatherList(): LiveData<List<WeatherDbModel>>

    @Query("SELECT * FROM weathers")
    suspend fun getWeatherListSuspend(): List<WeatherDbModel>

    @Query("SELECT * FROM weathers WHERE code == :code LIMIT 1")
    fun getWeather(code: Int): LiveData<WeatherDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(cell: WeatherDbModel)

    @Query("DELETE FROM weathers WHERE code == :code")
    suspend fun deleteWeather(code: Int)
}