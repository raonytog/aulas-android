package com.example.aula_2909

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class Preferences(context: Context, applicationContext: Any) {
    private var sp: SharedPreferences =
        context.getSharedPreferences("CHAVE_ACESSO", MODE_PRIVATE)

    fun setString(key: String, str: String) {
        sp.edit().putString(key, str).apply()
    }

    fun getString(key: String): String {
        return sp.getString(key, "") ?: ""
    }
}