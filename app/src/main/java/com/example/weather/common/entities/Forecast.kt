package com.example.weather.common.entities

data class Forecast(
    val dt: Long,
    val humidity: Int,
    val temp: Double,
    val  weather: List <Weather>, // es de tipo list porque la respuesta es un arreglo, ojo con los [
    val pop: Double
): WeatherBase(dt,humidity,temp,weather)

