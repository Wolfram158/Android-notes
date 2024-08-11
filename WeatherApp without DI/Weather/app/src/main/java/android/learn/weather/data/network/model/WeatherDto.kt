package android.learn.weather.data.network.model

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    val code: Int,
    @SerializedName("location") val locationDetailsDto: LocationDetailsDto,
    @SerializedName("current") val currentInfo: CurrentInfoDto
)