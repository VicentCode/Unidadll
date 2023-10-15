package com.vicentcode.unidadll.Practicas.P2.Menu

import Materia
import Unidad
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.vicentcode.unidadll.R
import com.vicentcode.unidadll.databinding.ActivityHomeFBinding
import eliminarTodasLasCalificaciones
import guardarMateria
import kotlinx.coroutines.delay
import obtenerCalificacionesFilter
import obtenerMaterias
import obtenerNombresMaterias

class Home : AppCompatActivity() {
    lateinit var v: ActivityHomeFBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        v = ActivityHomeFBinding.inflate(layoutInflater)
        setContentView(v.root)
        eliminarTodasLasCalificaciones(this)
/*
        // Crear la materia "Inglés"
        var materia = Materia(id = 1, nombre = "Inglés")

// Inicializa las unidades antes de establecer las calificaciones
        materia.unidades.add(Unidad())
        materia.unidades.add(Unidad())
        materia.unidades.add(Unidad())
        materia.unidades.add(Unidad())

// Ahora puedes establecer las calificaciones para cada unidad
        materia.unidades[0].calificacion = 85
        materia.unidades[1].calificacion = 90
        materia.unidades[2].calificacion = 78
        materia.unidades[3].calificacion = 92


        val materiamapa = Materia(id = 2, nombre = "mate")

// Inicializa las unidades antes de establecer las calificaciones
        materiamapa.unidades.add(Unidad())
        materiamapa.unidades.add(Unidad())
        materiamapa.unidades.add(Unidad())
        materiamapa.unidades.add(Unidad())

// Ahora puedes establecer las calificaciones para cada unidad
        materiamapa.unidades[0].calificacion = 12
        materiamapa.unidades[1].calificacion = 54
        materiamapa.unidades[2].calificacion = 23
        materiamapa.unidades[3].calificacion = 100

// Guarda la materia en SharedPreferences
        guardarMateria(this, materia)
        guardarMateria(this, materiamapa)
        */
        val materiasGuardadas = obtenerMaterias(this@Home)
        val materiaIngles = materiasGuardadas.find { it.nombre == "ingles" }
        v.textView5.text = materiaIngles.toString()


        if (obtenerMaterias(this).size == 0){
            loadFragment(AddMateriasF(), true)
            Toast.makeText(this, "Agrega una materia", Toast.LENGTH_SHORT).show()
        }else{
            loadFragment(AddMateriasF(), true)
        }


        val nombresMaterias = obtenerNombresMaterias(this)
        //v.textView5.text = nombresMaterias[0]
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombresMaterias)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        v.spinner.adapter = adapter

        v.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                v.textView5.text =  obtenerCalificacionesFilter(this@Home,nombresMaterias[position])[1].toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

    }



    private fun loadFragment(fragment: Fragment, type:Boolean) {

        val fragmentTransactionn = supportFragmentManager.beginTransaction()
        fragmentTransactionn.setCustomAnimations(R.anim.slide_in_right, 0, 0, 0)
        if (type){
            fragmentTransactionn.replace(R.id.frameHomeM, fragment)
        }else{
            fragmentTransactionn.add(R.id.frameHomeM, fragment)
        }
        fragmentTransactionn.commit()
    }
}