package com.example.kotlincourse

import android.content.Context
import android.preference.PreferenceManager

class CountManager(context: Context) {
    companion object {
        private const val WEB_COUNT = "data.source.prefs.WEB_COUNT"
        private const val DETAILS_COUNT = "data.source.prefs.DETAILS_COUNT"
    }
    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    var webCount: Int?
        get() = preferences.getInt(WEB_COUNT, 0)
        set(value) = preferences.edit().putInt(WEB_COUNT, value!!).apply()

    var detailsCount: Int?
        get() = preferences.getInt(DETAILS_COUNT, 0)
        set(value) = preferences.edit().putInt(DETAILS_COUNT, value!!).apply()
}