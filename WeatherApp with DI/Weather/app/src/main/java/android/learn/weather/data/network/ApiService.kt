package android.learn.weather.data.network

import android.learn.weather.data.network.model.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("current.json?key=")
    suspend fun getWeather(@Query(QUERY_PARAM) location: String): WeatherDto

    companion object {
        private const val QUERY_PARAM = "q"

    }
}