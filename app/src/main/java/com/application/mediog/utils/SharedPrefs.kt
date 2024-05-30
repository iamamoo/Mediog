package com.application.mediog.utils

import android.content.Context
import android.content.SharedPreferences

open class SharedPrefs {

    fun saveOnBoarding(context: Context, value: Boolean) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("isOnBoardingShowed", value)
        editor.apply()
    }

    fun getOnBoardingStatus(context: Context): Boolean {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isOnBoardingShowed", false)
    }

    fun deleteOnBoardingStatus(context: Context) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.remove("isOnBoardingShowed")
        editor.apply()
    }


    // login status

    fun saveLoginStatus(context: Context, value: Boolean) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("isUserLogin", value)
        editor.apply()
    }

    fun getLoginStatus(context: Context): Boolean {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isUserLogin", false)
    }

    fun deleteLoginStatus(context: Context) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.remove("isUserLogin")
        editor.apply()
    }
}