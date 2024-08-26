package android.learn.weather.data.mapper

import android.learn.weather.data.database.WeatherDbModel
import android.learn.weather.data.network.model.WeatherDto
import android.learn.weather.domain.Weather
import javax.inject.Inject

class WeatherMapper @Inject constructor() {
    fun mapDtoToDbModel(dto: WeatherDto) = WeatherDbModel(
        code = (dto.locationDetailsDto.name +
                dto.locationDetailsDto.region +
                dto.locationDetailsDto.country).hashCode(),
        name = dto.locationDetailsDto.name,
        region = dto.locationDetailsDto.region,
        country = dto.locationDetailsDto.country,
        localtime = dto.locationDetailsDto.localtime,
        lastUpdated = dto.currentInfo.lastUpdated,
        tempC = dto.currentInfo.temperature,
        imageUrl = dto.currentInfo.condition.imageUrl,
        cloud = dto.currentInfo.cloud,
        wind = dto.currentInfo.wind,
        dewPoint = dto.currentInfo.dewPoint,
        pressure = dto.currentInfo.pressure,
        humidity = dto.currentInfo.humidity
    )

    fun mapDbModelToEntity(dbModel: WeatherDbModel) =
        Weather(
            code = dbModel.code,
            name = dbModel.name,
            region = dbModel.region,
            country = dbModel.country,
            localtime = dbModel.localtime,
            lastUpdated = dbModel.lastUpdated,
            tempC = dbModel.tempC,
            imageUrl = dbModel.imageUrl,
            cloud = dbModel.cloud,
            wind = dbModel.wind,
            dewPoint = dbModel.dewPoint,
            pressure = dbModel.pressure,
            humidity = dbModel.humidity
        )

}