package com.juanparedes.forecastapp.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juanparedes.forecastapp.domain.model.FetchForecastState
import com.juanparedes.forecastapp.domain.model.Location
import com.juanparedes.forecastapp.domain.usecase.GetForecastByLocationUseCase
import com.juanparedes.forecastapp.domain.usecase.GetPossibleLocationsUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private val TAG = ForecastViewModel::class.simpleName

class ForecastViewModel @Inject constructor(
    private val locationsUseCase: GetPossibleLocationsUseCase,
    private val forecastByLocationUseCase: GetForecastByLocationUseCase
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val locationsLiveData = MutableLiveData<List<Location>>()
    private val forecastByLocationLiveData =
        MutableLiveData<FetchForecastState>(FetchForecastState.InitialState)

    private val searchPossibleLocationsSubject = PublishSubject.create<String>()
    private val locationsSubject = PublishSubject.create<String>()

    init {
        startPossibleLocationsObserver()
        startLocationObserver()
    }

    fun fetchForecastByLocation(location: String) {
        forecastByLocationLiveData.value = FetchForecastState.LoadingState
        locationsSubject.onNext(location)
    }

    fun findPossibleLocations(location: String) {
        searchPossibleLocationsSubject.onNext(location)
    }

    private fun startPossibleLocationsObserver() {
        compositeDisposable.add(
            searchPossibleLocationsSubject
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .switchMap { location ->
                    locationsUseCase.execute(location)
                        .toObservable()
                        .subscribeOn(Schedulers.io())
                }.observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { locationsList ->
                        locationsLiveData.value = locationsList
                    },
                    {
                        locationsLiveData.value = emptyList()
                        onError()
                    }
                )
        )
    }

    private fun startLocationObserver() {
        compositeDisposable.add(
            locationsSubject
                .flatMap { location ->
                    forecastByLocationUseCase.execute(location)
                        .toObservable()
                }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { forecast ->
                        forecastByLocationLiveData.value =
                            FetchForecastState.SearchResults(forecast)
                    },
                    {
                        forecastByLocationLiveData.value = FetchForecastState.ErrorState
                        onError()
                    },
                )

        )
    }

    private fun onError() {
        compositeDisposable.clear()
        startPossibleLocationsObserver()
        startLocationObserver()
    }

    fun getPossibleLocationsLivedata(): LiveData<List<Location>> = locationsLiveData

    fun getForecastByLocation(): LiveData<FetchForecastState> = forecastByLocationLiveData

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}