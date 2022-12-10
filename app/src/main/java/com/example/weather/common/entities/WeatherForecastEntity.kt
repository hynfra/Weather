package com.example.weather.common.entities

data class WeatherForecastEntity(
    val timeZone: String,
    val current: Current,
    val hourly: List<Forecast> // es de tipo list porque es un arreglo

    )
