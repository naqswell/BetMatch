package com.naqswell.allfootball.domain

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    val FILE_PREFS = "prefsBetmatch"

    val IS_DARK_THEME = "isDarkTheme"
    val IS_NOTIFICATION_ENABLED = "IsNotificationEnabled"
    val IS_BACKGROUND_CHANGED = "IsBackgroundChanged"

    private val appContext = context.applicationContext
    private val preferences: SharedPreferences =
        appContext.getSharedPreferences(FILE_PREFS, Context.MODE_PRIVATE)

    var isDarkTheme: Boolean
        get() = preferences.getBoolean(IS_DARK_THEME, false)
        set(value) = preferences.edit().putBoolean(IS_DARK_THEME, value).apply()

    var isNotificationEnabled: Boolean
        get() = preferences.getBoolean(IS_NOTIFICATION_ENABLED, false)
        set(value) = preferences.edit().putBoolean(IS_NOTIFICATION_ENABLED, value).apply()

    var isBackgroundChanged: Boolean
        get() = preferences.getBoolean(IS_BACKGROUND_CHANGED, false)
        set(value) = preferences.edit().putBoolean(IS_BACKGROUND_CHANGED, value).apply()
}