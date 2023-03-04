package com.naqswell.allfootball.screens

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.naqswell.allfootball.MyApplication
import com.naqswell.allfootball.R
import com.naqswell.allfootball.databinding.MainActivityBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun getTheme(): Resources.Theme {
        val theme = super.getTheme()
        when ((application as MyApplication).prefs.isDarkTheme) {
            true -> {
                when ((application as MyApplication).prefs.isBackgroundChanged) {
                    true -> theme.applyStyle(R.style.Theme_AllFootballDark2, true)
                    false -> theme.applyStyle(R.style.Theme_AllFootballDark1, true)
                }
            }
            false -> {
                when ((application as MyApplication).prefs.isBackgroundChanged) {
                    true -> theme.applyStyle(R.style.Theme_AllFootballLight2, true)
                    false -> theme.applyStyle(R.style.Theme_AllFootballLight1, true)
                }
            }
        }

        return theme
    }
}




