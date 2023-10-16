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
import deleteAll
import guardarMateria
import kotlinx.coroutines.delay
import obtenerCalif
import obtenerMateriasALL

class Home : AppCompatActivity() {
    lateinit var v: ActivityHomeFBinding
    private var materias: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        v = ActivityHomeFBinding.inflate(layoutInflater)
        setContentView(v.root)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, obtenerMateriasALL(this))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        v.spinner.adapter = adapter

        v.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                materias = v.spinner.selectedItem.toString()
                // obtenerMateriasALL(this@Home)[position].toString()
                if (obtenerMateriasALL(this@Home).size == 0){
                    loadFragment(AddMateriasF(), true, null, null)
                    Toast.makeText(this@Home, "Agrega una materia", Toast.LENGTH_SHORT).show()
                }else{
                    loadFragment(FragmentConsultaM(), true, "mat", materias)
                }

            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }



        v.button3.setOnClickListener {
            loadFragment(AddMateriasF(), true, "mat", materias)
        }


    }



    private fun loadFragment(fragment: Fragment, type: Boolean, variableName: String?, variableValue: String?) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, 0, 0, 0)

        // Crea un Bundle para enviar datos al fragmento
        val args = Bundle()
        args.putString(variableName, variableValue)

        // Asigna los argumentos al fragmento
        fragment.arguments = args

        if (type) {
            fragmentTransaction.replace(R.id.frameHomeM, fragment)
        } else {
            fragmentTransaction.add(R.id.frameHomeM, fragment)
        }

        fragmentTransaction.commit()
    }

}