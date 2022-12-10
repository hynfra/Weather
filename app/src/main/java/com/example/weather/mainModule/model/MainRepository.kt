package com.example.weather.mainModule.model

import com.example.weather.common.entities.WeatherForecastEntity

class MainRepository {
    private val remoteDatabase = RemoteDatabase() // instanciamos la clase que crea el retrofit en base a los parametros de nuestra interfaz

    suspend fun getWeatherAndForecast(lat: Double, lon: Double, appId: String, units: String,
                                      lang: String) : WeatherForecastEntity =
        remoteDatabase.getWeatherForecastByCoordinates(lat,lon,appId,units,lang) // se asigna metodo que almacena
}