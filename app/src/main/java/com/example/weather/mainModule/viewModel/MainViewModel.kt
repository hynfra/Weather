package com.example.weather.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.R
import com.example.weather.common.entities.WeatherForecastEntity
import com.example.weather.mainModule.model.MainRepository
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val repository = MainRepository() // se instancia el repositorio

    private val result = MutableLiveData<WeatherForecastEntity>() // instancia en formato arreglo de la entidad forecast
    fun getResult(): LiveData<WeatherForecastEntity> = result // se agrega la instancia al metodo getResult

    private val snackbarMsg = MutableLiveData<Int>() // lista de int
    fun getSnackbarMsg() = snackbarMsg

    private val loaded = MutableLiveData<Boolean>() // lista de boolean
    fun isLoaded() = loaded

    suspend fun getWeatherAndForecast(lat: Double, lon: Double, appId: String, units: String,
                                      lang: String){ // metodo que se crea con todos los parametro de la api
        viewModelScope.launch {
            try {
                loaded.value = false
                val resultServer = repository.getWeatherAndForecast(lat,lon,appId,units,lang) // establece el repositorio con los parametro de la api
                result.value = resultServer  // agrega al resultado
            }catch (e: Exception){
                snackbarMsg.value = R.string.main_error_server
            }finally {
                loaded.value = true

            }
        }
    }
}