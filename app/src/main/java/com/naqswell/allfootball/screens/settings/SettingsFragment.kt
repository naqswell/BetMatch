package com.naqswell.allfootball.screens.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.naqswell.allfootball.MyApplication
import com.naqswell.allfootball.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        return binding.apply {

            switchDarkTheme.apply {
                isChecked = (requireActivity().application as MyApplication).prefs.isDarkTheme
                setOnCheckedChangeListener { _, isChecked ->
                    (requireActivity().application as MyApplication).prefs.isDarkTheme = isChecked
                    requireActivity().recreate()
                }
            }

            switchNotification.apply {
                isChecked = (requireActivity().application as MyApplication).prefs.isNotificationEnabled
                setOnCheckedChangeListener { _, isChecked ->
                    (requireActivity().application as MyApplication).prefs.isNotificationEnabled = isChecked
                }
            }

            btnChange.setOnClickListener {
                val prevValue = (requireActivity().application as MyApplication).prefs.isBackgroundChanged
                (requireActivity().application as MyApplication).prefs.isBackgroundChanged = !prevValue
                requireActivity().recreate()
            }
        }.root
    }
}