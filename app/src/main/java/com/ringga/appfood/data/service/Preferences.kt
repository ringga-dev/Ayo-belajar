package com.ringga.appfood.data.service

import android.content.Context
import android.content.SharedPreferences


object Preferences {
    private const val PREFS = "SoundPreferences"
    fun setMediaPosition(ctx: Context, position: Int) {
        val prefs: SharedPreferences = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        prefs.edit().putInt("position", position).apply()
    }

    fun getMediaPosition(ctx: Context): Int {
        val prefs: SharedPreferences = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        return prefs.getInt("position", 0)
    }

    fun setMediaUrl(ctx: Context, url: String) {
        val prefs: SharedPreferences = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        prefs.edit().putString("url", url).apply()
    }

    fun getMediaUrl(ctx: Context): String? {
        val prefs: SharedPreferences = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        return prefs.getString("url", null)
    }

    fun setMediaPositionLagu(ctx: Context, lagu: Int) {
        val prefs: SharedPreferences = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        prefs.edit().putInt("lagu", lagu).apply()
    }

    fun getMediaPositionLagu(ctx: Context): Int {
        val prefs: SharedPreferences = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        return prefs.getInt("lagu", 0)
    }

    fun setMediaDurasiLagu(ctx: Context, durasi: Int) {
        val prefs: SharedPreferences = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        prefs.edit().putInt("durasi", durasi).apply()
    }

    fun getMediaDurasiLagu(ctx: Context): Int {
        val prefs: SharedPreferences = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        return prefs.getInt("durasi", 0)
    }

    fun clearMedia(ctx: Context) {
        val settings: SharedPreferences = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        settings.edit().clear().apply()
    }
}