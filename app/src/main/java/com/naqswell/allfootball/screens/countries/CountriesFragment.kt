package com.naqswell.allfootball.screens.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.naqswell.allfootball.MyApplication
import com.naqswell.allfootball.databinding.FragmentCountriesBinding
import com.naqswell.allfootball.domain.enums.Countries

class CountriesFragment : Fragment() {
    private var _binding: FragmentCountriesBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: CountriesViewModel by activityViewModels {
        CountriesViewModel.provideFactory(((requireActivity().application) as MyApplication).allSportsApi)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountriesBinding.inflate(inflater, container, false)
        sharedViewModel.fetchTournamentTables()

        return binding.apply {
            setViewModelListeners()
            setButtonsClickListeners()
        }.root
    }

    private fun setButtonsClickListeners() {
        with(binding) {
            btnRussia.setOnClickListener {
                onBtnCountryClicked(Countries.Russia)
            }

            btnEngland.setOnClickListener {
                onBtnCountryClicked(Countries.England)
            }

            btnGermany.setOnClickListener {
                onBtnCountryClicked(Countries.Germany)
            }

            btnSpain.setOnClickListener {
                onBtnCountryClicked(Countries.Spain)
            }
        }
    }


    private fun setViewModelListeners() {
        sharedViewModel.tablesList.observe(viewLifecycleOwner) {
        }

        sharedViewModel.onFetchFailed.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun onBtnCountryClicked(countryType: Countries) {
        findNavController().navigate(CountriesFragmentDirections.actionToTable(countryType))
    }
}