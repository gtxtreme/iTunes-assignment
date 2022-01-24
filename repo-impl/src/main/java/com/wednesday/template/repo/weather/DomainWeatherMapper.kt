package com.wednesday.template.repo.weather

import com.wednesday.template.domain.weather.Weather
import com.wednesday.template.repo.util.Mapper
import com.wednesday.template.service.weather.LocalCityWithWeather
import timber.log.Timber

interface DomainWeatherMapper : Mapper<LocalCityWithWeather, Weather>

class DomainWeatherMapperImpl(
    private val dayWeatherMapper: DomainDayWeatherMapper
) : DomainWeatherMapper {

    override fun map(from: LocalCityWithWeather): Weather {
        Timber.tag(TAG).d("map() called with: from = $from")
        return Weather(
            title = from.weather.title,
            woeid = from.city.woeid,
            dayWeatherList = dayWeatherMapper.map(from.dayWeather)
        )
    }

    companion object {
        private const val TAG = "DomainWeatherMapperImpl"
    }
}
