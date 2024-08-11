package android.learn.weather.data.network.model

import com.google.gson.annotations.SerializedName

data class StateDto(
    @SerializedName("text") val text: String,
    @SerializedName("icon") val imageUrl: String
)

