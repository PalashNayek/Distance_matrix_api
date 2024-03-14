package com.palash.distance_matrix_api.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.gms.maps.model.LatLng
import com.palash.distance_matrix_api.R
import com.palash.distance_matrix_api.databinding.FragmentHomeBinding
import com.palash.distance_matrix_api.view_model.DistanceMatrixViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val distanceViewModel by viewModels<DistanceMatrixViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val origin = LatLng(37.7749, -122.4194) // San Francisco, CA
        val destination = LatLng(34.0522, -118.2437) // Los Angeles, CA
        distanceViewModel.calculateDistance(origin, destination)

        distanceViewModel.distanceText.observe(viewLifecycleOwner, Observer { distance ->
            binding.txt.text = distance.toString()
            Log.d("MyRes", distance.toString())
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}