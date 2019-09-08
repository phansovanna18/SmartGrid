package com.example.smartgrid.fetchData

import android.content.Context
import android.content.SharedPreferences

class UsersLoginSharedPreference(context: Context){

    private var boolLogin = false
    private var nameSharedPreference = "Energy"
    private val nameEmail = "Email"
    private val namePassword = "Password"
    private val nameLogin = "Login"

    private val objectSharedPreference:SharedPreferences
    private val objectSharedPreferenceEdit:SharedPreferences.Editor

    init {
        objectSharedPreference = context.getSharedPreferences(nameSharedPreference,Context.MODE_PRIVATE)
        objectSharedPreferenceEdit = objectSharedPreference.edit()
        objectSharedPreferenceEdit.apply()
    }

//    fun getEmail():String?{
//        return objectSharedPreference.getString(nameEmail,"")
//    }
//
//    fun getPassword():String?{
//        return objectSharedPreference.getString(namePassword,"")
//    }

    fun getLogin():Boolean?{
        return objectSharedPreference.getBoolean(nameLogin,boolLogin)
    }

    fun setEmail(email:String){
        objectSharedPreferenceEdit.putString(nameEmail,email)
        objectSharedPreferenceEdit.apply()
    }

    fun setPassword(password:String){
        objectSharedPreferenceEdit.putString(namePassword,password)
        objectSharedPreferenceEdit.apply()
    }

    fun setLogin(login:Boolean){
        objectSharedPreferenceEdit.putBoolean(nameLogin, login)
        objectSharedPreferenceEdit.apply()
    }
}