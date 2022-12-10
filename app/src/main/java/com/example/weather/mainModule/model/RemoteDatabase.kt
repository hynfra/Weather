package com.example.weather.mainModule.model

import com.example.weather.common.dataAccess.WeatherService
import com.example.weather.common.entities.WeatherForecastEntity
import com.example.weather.common.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDatabase {
    // retrofit es una forma de consumir API
    private val retrofit = Retrofit.Builder() // inicia el retrofit
        .baseUrl(Constants.BASE_URL) // establece la direccion de la pagina web donde se consumira API
        .addConverterFactory(GsonConverterFactory.create()) // se convierte la informacion JSON hacia codigo java (model) objects
        .build() //construye el retrofit con los parametros anteriores
    private val service = retrofit.create(WeatherService::class.java) // usa el retrofit segun la interfaz WeatherService

    suspend fun getWeatherForecastByCoordinates(lat: Double, lon: Double, appId: String,
                                                units: String, lang: String) : WeatherForecastEntity =
        withContext(Dispatchers.IO){
        // establece el dispatchers de las corrutinas
        service.getWeatherForecastByCoordinates(lat,lon,appId,units,lang) // usa el retrofit con la interfaz para agregar los parametros de la funcion establecida

    }
}