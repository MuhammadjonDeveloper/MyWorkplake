package com.e.myworkplace

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager

class App : Application() {
    private var name: String? = null
    private var isUpdating: Boolean? = null
    private var preferences: SharedPreferences? = null

    fun getName(): String? {
        name = preferences!!.getString("KEY", "0000")
        return name
    }

    fun setName(name: String) {
        preferences!!.edit().putString("KEY", name).apply()
    }

    fun setUpdating(change: Boolean) {
        preferences!!.edit().putBoolean("key", change).apply()
    }

    fun getUpdating(): Boolean = preferences!!.getBoolean("key", true)


    override fun onCreate() {
        super.onCreate()
        preferences = PreferenceManager.getDefaultSharedPreferences(this)
    }
}