package android.learn.weather.data.network.model

import com.google.gson.annotations.SerializedName

data class LocationDetailsDto (
    @SerializedName("name") val name: String,
    @SerializedName("region") val region: String,
    @SerializedName("country") val country: String,
    @SerializedName("localtime") val localtime: String
)
