package com.mantelpiecesb.weatherclient.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mantelpiecesb.weatherclient.model.Weather
import com.mantelpiecesb.weatherclient.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    private val _response = MutableLiveData<Weather>()
    private val _errorMessage = MutableLiveData<String>()

    val weatherResponse: LiveData<Weather>
        get() = _response

    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val disposables = CompositeDisposable()

    init {
        getWeather()
    }

    private fun getWeather() {
        disposables.add(
            repository
                .getWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::useData, ::handleError)
        )
    }

    private fun useData(weather: Weather?) {
        _response.postValue(weather)
    }

    fun handleError(throwable: Throwable?) {
        _errorMessage.postValue("Error: ${throwable?.localizedMessage}")
    }

    fun stop() {
        disposables.clear()
    }

}












