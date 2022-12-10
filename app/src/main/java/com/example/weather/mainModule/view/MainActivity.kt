package com.example.weather.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.weather.BR
import com.example.weather.R
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.mainModule.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main) // se usa  databinding

        setupViewModel()
        setupObservers()
    }

    private fun setupViewModel() {
        val vm: MainViewModel by viewModels()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, vm)

    }

    private fun setupObservers() {
       binding.viewModel?.let {
           it.getSnackbarMsg().observe(this){resMsg -> // resMsg viene del mensaje de mainviewmodel donde apunta al recurso string (R)
               Snackbar.make(binding.root,resMsg,Snackbar.LENGTH_LONG).show()

           }
       }
    }

    override fun onStart() {
        super.onStart()
        //corrutina del ciclo de vida
        lifecycleScope.launch {
            binding.viewModel?.getWeatherAndForecast(-33.4489, -70.6693
                ,"8db4027cb6ddc310113edac1472f121a","metric ","en") // la localizacion se puede ver en el mapa
        }
    }
}