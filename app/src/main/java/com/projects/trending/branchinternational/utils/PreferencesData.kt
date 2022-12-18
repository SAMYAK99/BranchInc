package com.projects.trending.branchinternational.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

// Preferences class for storing token

object PreferenceData {
    const val PREF_LOGGEDIN_TOKEN = "logged_token"

    fun getSharedPreferences(ctx: Context?): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    fun setLoggedInUserUid(ctx: Context?, uid: String?) {
        val editor = getSharedPreferences(ctx).edit()
        editor.putString(PREF_LOGGEDIN_TOKEN, uid)
        editor.commit()
    }

    fun getLoggedInUserUid(ctx: Context?): String? {
        return getSharedPreferences(ctx).getString(PREF_LOGGEDIN_TOKEN, "")
    }

    fun clearLoggedInUserId(ctx: Context?) {
        val editor = getSharedPreferences(ctx).edit()
        editor.remove(PREF_LOGGEDIN_TOKEN)
        editor.commit()
    }
}