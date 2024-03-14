package com.palash.distance_matrix_api.di

import android.app.Application
import com.google.maps.GeoApiContext
import com.palash.distance_matrix_api.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideGeoApiContext(application: Application): GeoApiContext {
        return GeoApiContext.Builder()
            .apiKey(application.getString(R.string.google_maps_api_key))
            .build()
    }
}