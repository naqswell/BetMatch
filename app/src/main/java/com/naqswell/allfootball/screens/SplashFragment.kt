package com.naqswell.allfootball.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.naqswell.allfootball.Constants
import com.naqswell.allfootball.databinding.FragmentSplashBinding
import kotlinx.coroutines.*

class SplashFragment : Fragment() {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.apply {

            coroutineScope.launch {
                delay(Constants.SPLASH_SCREEN_TIME)
                view?.let {
                    Navigation.findNavController(it)
                        .navigate(SplashFragmentDirections.actionToMain())
                }
            }
        }.root
    }
}