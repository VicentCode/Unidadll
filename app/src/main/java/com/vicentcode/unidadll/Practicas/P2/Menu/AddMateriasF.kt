package com.vicentcode.unidadll.Practicas.P2.Menu

import Materia
import Unidad
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.vicentcode.unidadll.databinding.FragmentAddMateriasBinding
import guardarCalif
import guardarMateria
import obtenerCalif
import obtenerMateriasALL

class AddMateriasF : Fragment() {

    private lateinit var v: FragmentAddMateriasBinding
    var miVariable = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Recuperar los argumentos del fragmento
        val arguments = arguments

        if (arguments != null) {
            miVariable = arguments.getString("mat", "ERROR 404")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = FragmentAddMateriasBinding.inflate(inflater, container, false)

        v.guardar.setOnClickListener {
            if (verifyInputs()){
                val materia = v.matNameED.text.toString()
                if (obtenerMateriasALL(requireContext()).contains(materia)) {
                    CreateMateria()
                    Toast.makeText(requireContext(), "$materia actualizada con exito", Toast.LENGTH_SHORT).show()
                }else{
                    CreateMateria()
                    Toast.makeText(requireContext(), "$materia creada y guardada con exito", Toast.LENGTH_SHORT).show()
                }

                }
            }

        v.matNameED.setText(miVariable)
        v.U1ED.setText(obtenerCalif(requireContext(), miVariable, "1"))
        v.U2ED.setText(obtenerCalif(requireContext(), miVariable, "2"))
        v.U3ED.setText(obtenerCalif(requireContext(), miVariable, "3"))
        v.u4ED.setText(obtenerCalif(requireContext(), miVariable, "4"))


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
        val unidad1 = v.U1ED.text.toString()
        val unidad2 = v.U2ED.text.toString()
        val unidad3 = v.U3ED.text.toString()
        val unidad4 = v.u4ED.text.toString()
        guardarMateria(requireContext(), materiaNameED)
        guardarCalif(requireContext(), materiaNameED, unidad1,unidad2, unidad3, unidad4)


    }

    fun ClearInputs(){
        v.matNameED.text?.clear()
        v.U1ED.text?.clear()
        v.U2ED.text?.clear()
        v.U3ED.text?.clear()
        v.u4ED.text?.clear()
    }
}
