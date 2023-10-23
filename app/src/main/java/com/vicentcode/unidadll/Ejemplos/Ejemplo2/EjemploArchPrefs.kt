package com.vicentcode.unidadll.Ejemplos.Ejemplo2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vicentcode.unidadll.databinding.ActivityEjemploArchPrefsBinding

class EjemploArchPrefs : AppCompatActivity() {

    var editor:Editor?=null

    private lateinit var v: ActivityEjemploArchPrefsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        v = ActivityEjemploArchPrefsBinding.inflate(layoutInflater)
        val view = v.root
        setContentView(view)

        var prefs = getSharedPreferences("datos",Context.MODE_PRIVATE)
        editor = prefs.edit()

        v.button.setOnClickListener {
            editor?.putString("name", v.editTextText.text.toString())
            editor?.putString("carr", v.editTextText2.text.toString())
            editor?.commit()

        }

        v.button2.setOnClickListener {
            val intent = Intent(this@EjemploArchPrefs, ArchPrefs2::class.java)
            startActivity(intent)
        }
    }
}