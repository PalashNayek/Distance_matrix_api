package com.palash.distance_matrix_api.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.maps.DistanceMatrixApi
import com.google.maps.GeoApiContext
import com.google.maps.model.DistanceMatrix
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DistanceMatrixViewModel @Inject constructor(private val geoApiContext: GeoApiContext) : ViewModel(){

    private val _distanceText = MutableLiveData<String>()
    val distanceText: LiveData<String> = _distanceText

    fun calculateDistance(origin: LatLng, destination: LatLng) {
        viewModelScope.launch {
            val distanceMatrixApiRequest = DistanceMatrixApi.newRequest(geoApiContext)
                .origins(toApiLatLng(origin))
                .destinations(toApiLatLng(destination))

            try {
                val result: DistanceMatrix = distanceMatrixApiRequest.await()

                // Extract distance information from the result and update LiveData
                val distance = result.rows[0].elements[0].distance
                _distanceText.postValue(distance.humanReadable)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun toApiLatLng(latLng: LatLng): com.google.maps.model.LatLng {
        return com.google.maps.model.LatLng(latLng.latitude, latLng.longitude)
    }
}