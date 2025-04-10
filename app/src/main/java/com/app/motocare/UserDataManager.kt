package com.app.motocare

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object UserDataManager {
    private const val PREFS_NAME = "MotoCarePrefs"
    private const val USERS_KEY = "users"

    data class Motorcycle(var makeModel: String, val mileage: String) // Define Motorcycle data class here

    private val users: MutableMap<String, Pair<String, MutableList<Motorcycle>>> = mutableMapOf()

    fun addUser(email: String, password: String) {
        users[email] = Pair(password, mutableListOf())
    }

    fun getUser(email: String): Pair<String, MutableList<Motorcycle>>? {
        return users[email]
    }

    fun addMotorcycle(email: String, motorcycle: Motorcycle) {
        users[email]?.second?.add(motorcycle)
    }

    fun getAllUsers(): MutableMap<String, Pair<String, MutableList<Motorcycle>>> {
        return users
    }

    fun loadData(context: Context) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val usersJson = prefs.getString(USERS_KEY, null)

        if (usersJson != null) {
            val typeToken = object : TypeToken<MutableMap<String, Pair<String, MutableList<Motorcycle>>>>() {}.type
            val loadedUsers: MutableMap<String, Pair<String, MutableList<Motorcycle>>> =
                Gson().fromJson(usersJson, typeToken)
            users.putAll(loadedUsers)
        }
    }

    fun saveData(context: Context) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        val usersJson = Gson().toJson(users)
        editor.putString(USERS_KEY, usersJson)
        editor.apply()
    }
}