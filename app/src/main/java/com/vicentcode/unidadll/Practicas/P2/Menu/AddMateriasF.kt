package com.vicentcode.unidadll.Practicas.P2.Menu

import Materia
import Unidad
import actualizarMateria
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.vicentcode.unidadll.databinding.FragmentAddMateriasBinding
import guardarMateria
import materiaExiste

class AddMateriasF : Fragment() {

    private lateinit var v: FragmentAddMateriasBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = FragmentAddMateriasBinding.inflate(inflater, container, false)

        v.guardar.setOnClickListener {
            if (verifyInputs()){
                //create or update materia
                CreateMateria()
                } else {

                }
            }



        return v.root
    }

    fun verifyInputs(): Boolean{
        var band = true
        if (v.matNameED.text.toString().isEmpty()){
            v.matNameL.error = "Ingrese un nombre de materia"
            band = false
            return band
        }else{
            v.matNameL.error = null
        }
        if (v.U1ED.text.toString().isEmpty()){
            v.U1L.error = "Ingrese una calificación para la unidad 1"
            band = false
            return band
        }else{
            v.U1L.error = null
        }
        if (v.U2ED.text.toString().isEmpty()){
            v.U2L.error = "Ingrese una calificación para la unidad 2"
            band = false
            return band
        }else{
            v.U2L.error = null
        }
        if (v.U3ED.text.toString().isEmpty()){
            v.u3L.error = "Ingrese una calificación para la unidad 3"
            band = false
            return band
        }else{
            v.u3L.error = null
        }
        if (v.u4ED.text.toString().isEmpty()){
            v.U4l.error = "Ingrese una calificación para la unidad 4"
            band = false
            return band
        }else{
            v.U4l.error = null
        }
        return band
    }

    fun CreateMateria()
    {
        val materiaNameED = v.matNameED.text.toString()
        val unidad1 = v.U1ED.text.toString().toInt()
        val unidad2 = v.U2ED.text.toString().toInt()
        val unidad3 = v.U3ED.text.toString().toInt()
        val unidad4 = v.u4ED.text.toString().toInt()

        if (!materiaExiste(requireContext(), materiaNameED)) {
            // Crear una instancia de Materia con un nombre y calificaciones para las unidades
            val materia = Materia(nombre = "ingles")
            materia.unidades.add(Unidad(calificacion = 85))
            materia.unidades.add(Unidad(calificacion = 90))
            materia.unidades.add(Unidad(calificacion = 78))
            materia.unidades.add(Unidad(calificacion = 92))

// Llamar a la función guardarMateria para guardar la materia
            guardarMateria(requireContext(), materia)

            ClearInputs()
            Toast.makeText(requireContext(), "Materia: $materiaNameED creada con exito", Toast.LENGTH_SHORT).show()
        }else{
            var materia = Materia( nombre = materiaNameED)
            materia.unidades.add(Unidad())
            materia.unidades.add(Unidad())
            materia.unidades.add(Unidad())
            materia.unidades.add(Unidad())
            materia.unidades[0].calificacion = unidad1
            materia.unidades[1].calificacion = unidad2
            materia.unidades[2].calificacion = unidad3
            materia.unidades[3].calificacion = unidad4

            guardarMateria(requireContext(), materia)
            // Supongamos que tienes una materia con ID 1 que deseas actualizar
            val materiaAActualizar = Materia( nombre = materiaNameED)

// Crea una nueva instancia de Materia con la información actualizada
            val nuevaInformacionMateria = Materia( nombre = materiaNameED)

// Llama a la función para actualizar la materia
            actualizarMateria(requireContext(), nuevaInformacionMateria)
            Toast.makeText(requireContext(), "Materia: $materiaNameED actualizada con exito", Toast.LENGTH_SHORT).show()

// La materia con ID 1 se actualizará con la nueva información en SharedPreferences

        }
    }

    fun ClearInputs(){
        v.matNameED.text?.clear()
        v.U1ED.text?.clear()
        v.U2ED.text?.clear()
        v.U3ED.text?.clear()
        v.u4ED.text?.clear()
    }
}
