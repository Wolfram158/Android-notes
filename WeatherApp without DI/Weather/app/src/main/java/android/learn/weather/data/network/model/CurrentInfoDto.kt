package android.learn.weather.data.network.model

import com.google.gson.annotations.SerializedName

data class CurrentInfoDto(
    @SerializedName("last_updated") val lastUpdated: String,
    @SerializedName("temp_c") val temperature: String,
    @SerializedName("condition") val condition: StateDto,
    @SerializedName("wind_kph") val wind: String,
    @SerializedName("pressure_mb") val pressure: String,
    @SerializedName("humidity") val humidity: String,
    @SerializedName("cloud") val cloud: String,
    @SerializedName("dewpoint_c") val dewPoint: String
)