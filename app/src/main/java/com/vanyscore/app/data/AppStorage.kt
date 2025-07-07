package com.vanyscore.app.data

import android.content.Context
import com.vanyscore.app.theme.AppTheme

interface IAppStorage {
    fun getThemeType(): AppTheme
    fun setThemeType(theme: AppTheme)
}

private const val APP_STORAGE_NAME = "APP_STORAGE"
private const val APP_STORAGE_THEME_KEY = "APP_STORAGE"

class AppStorage(private val context: Context) : IAppStorage {
    override fun getThemeType(): AppTheme {
        val prefs = context.getSharedPreferences(APP_STORAGE_NAME, Context.MODE_PRIVATE)
        val themeType = prefs.getString(APP_STORAGE_THEME_KEY, null)
        return if (themeType != null) AppTheme.valueOf(themeType) else AppTheme.YELLOW_LIGHT
    }

    override fun setThemeType(theme: AppTheme) {
        val prefs = context.getSharedPreferences(APP_STORAGE_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(APP_STORAGE_THEME_KEY, theme.name).apply()
    }
}