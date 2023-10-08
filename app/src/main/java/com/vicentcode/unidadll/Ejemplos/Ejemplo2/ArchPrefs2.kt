package com.vicentcode.unidadll.Ejemplos.Ejemplo2

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vicentcode.unidadll.R
import com.vicentcode.unidadll.databinding.ActivityArchPrefs2Binding

class ArchPrefs2 : AppCompatActivity() {

    var prefs:SharedPreferences?=null

    private lateinit var v: ActivityArchPrefs2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        v = ActivityArchPrefs2Binding.inflate(layoutInflater)
        val view = v.root
        setContentView(view)

        var prefs = getSharedPreferences("datos", Context.MODE_PRIVATE)

        v.nombre.text = prefs.getString("name", "null")
        v.carrera.text = prefs.getString("carr", "null")
    }
}