package com.naqswell.allfootball.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.naqswell.allfootball.MyApplication
import com.naqswell.allfootball.databinding.FragmentMainBinding
import com.naqswell.allfootball.screens.countries.CountriesViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.apply {

            btnTable.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionToCountries())
            }

            btnStats.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionToStats())
            }

            btnSettings.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionToSettings())
            }

            btnNews.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionToNews())
            }

        }.root
    }
}