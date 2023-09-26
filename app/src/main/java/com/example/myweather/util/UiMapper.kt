package com.example.myweather.util

import com.example.myweather.models.*

class UiMapper : Mapper<WeatherResponse, WeatherUI> {
    override fun mapFromDomain(domain: WeatherResponse): WeatherUI {
        return WeatherUI(
            base = domain.base,
            all = domain.clouds.all,
            cod = domain.cod,
            lat = domain.coord.lat,
            lon = domain.coord.lon,
            dt = domain.dt,
            id = domain.id,
            feels_like = domain.main.feels_like,
            humidity = domain.main.humidity,
            pressure = domain.main.pressure,
            temp = domain.main.temp,
            temp_max = domain.main.temp_max,
            temp_min = domain.main.temp_min,
            name = domain.name,
            country = domain.sys.country,
            sysId = domain.sys.id,
            sunrise = domain.sys.id,
            sunset = domain.sys.sunset,
            type = domain.sys.type,
            timezone = domain.timezone,
            visibility = domain.visibility,
            weather = domain.weather,
            deg = domain.wind.deg,
            gust = domain.wind.gust,
            speed = domain.wind.speed
        )
    }

    override fun mapToDomain(domain: WeatherUI): WeatherResponse {
        TODO("Dont need so I am not going to spend time on this")
    }
}

class DBMapper : Mapper<WeatherResponse, WeatherEntity> {
    override fun mapFromDomain(domain: WeatherResponse): WeatherEntity {
        return WeatherEntity(
            base = domain.base,
            all = domain.clouds.all,
            cod = domain.cod,
            lat = domain.coord.lat,
            lon = domain.coord.lon,
            dt = domain.dt,
            id = domain.id,
            feels_like = domain.main.feels_like,
            humidity = domain.main.humidity,
            pressure = domain.main.pressure,
            temp = domain.main.temp,
            temp_max = domain.main.temp_max,
            temp_min = domain.main.temp_min,
            name = domain.name,
            country = domain.sys.country,
            sysId = domain.sys.id,
            sunrise = domain.sys.id,
            sunset = domain.sys.sunset,
            type = domain.sys.type,
            timezone = domain.timezone,
            visibility = domain.visibility,
            deg = domain.wind.deg,
            gust = domain.wind.gust,
            speed = domain.wind.speed
        )
    }

    override fun mapToDomain(domain: WeatherEntity): WeatherResponse {
        return WeatherResponse(
            base = domain.base,
            clouds = Clouds(domain.all),
            cod = domain.cod,
            coord = Coord(domain.lat, domain.lon),
            dt = domain.dt,
            id = domain.id,
            main = Main(
                domain.feels_like,
                domain.humidity,
                domain.pressure,
                domain.temp,
                domain.temp_max,
                domain.temp_min
            ),
            name = domain.name,
            sys = Sys(domain.country, domain.sysId, domain.sunrise, domain.sunset, domain.type),
            timezone = domain.timezone,
            visibility = domain.visibility,
            weather = listOf(), //
            wind = Wind(domain.deg, domain.gust, domain.speed)
        )
    }

}