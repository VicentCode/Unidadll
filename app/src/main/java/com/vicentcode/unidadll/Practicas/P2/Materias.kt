package com.vicentcode.unidadll.Practicas.P2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.vicentcode.unidadll.Practicas.P2.Logins.Login
import com.vicentcode.unidadll.Practicas.P2.Logins.Register
import com.vicentcode.unidadll.Practicas.P2.Menu.Home
import com.vicentcode.unidadll.R
import com.vicentcode.unidadll.databinding.ActivityMateriasBinding

private lateinit var v: ActivityMateriasBinding


class Materias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        v = ActivityMateriasBinding.inflate(layoutInflater)
        setContentView(v.root)

        val sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("session", false)) {
            val intent = Intent(this@Materias, Home::class.java)
            startActivity(intent)
            finish()
        }else{
            val fragment = Login()
            supportFragmentManager.beginTransaction().replace(R.id.frameMaterias, fragment)
                .commit()
        }
    }
}