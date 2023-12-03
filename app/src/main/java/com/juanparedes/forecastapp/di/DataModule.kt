package com.juanparedes.forecastapp.di

import com.juanparedes.forecastapp.BASE_URL
import com.juanparedes.forecastapp.data.api.ForecastAppApi
import com.juanparedes.forecastapp.data.api.ForecastAppApiClient
import com.juanparedes.forecastapp.data.repository.ForecastRepositoryImpl
import com.juanparedes.forecastapp.data.repository.LocationRepositoryImpl
import com.juanparedes.forecastapp.domain.repository.ForecastRepository
import com.juanparedes.forecastapp.domain.repository.LocationRepository
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class DataModule {

    @Provides
    internal fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build()
            ).baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

    @Provides
    internal fun provideForecastAppApiClient(retrofit: Retrofit): ForecastAppApiClient =
        ForecastAppApiClient(
            retrofit.create(ForecastAppApi::class.java)
        )

    @Provides
    internal fun provideLocationRepository(
        client: ForecastAppApiClient
    ): LocationRepository = LocationRepositoryImpl(client)

    @Provides
    internal fun provideForecastRepository(
        client: ForecastAppApiClient
    ): ForecastRepository = ForecastRepositoryImpl(client)
}