package android.learn.weather.domain

data class Weather(
    val code: Int,
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