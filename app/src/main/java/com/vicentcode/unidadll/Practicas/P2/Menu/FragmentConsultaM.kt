package com.vicentcode.unidadll.Practicas.P2.Menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vicentcode.unidadll.databinding.FragmentConsultaMBinding
import obtenerCalif

class FragmentConsultaM : Fragment() {

    private lateinit var v: FragmentConsultaMBinding
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
        v = FragmentConsultaMBinding.inflate(inflater, container, false)

        v.MatName.text = miVariable


        v.CalU1.text = obtenerCalif(requireContext(), miVariable, "1")
        v.calU2.text = obtenerCalif(requireContext(), miVariable, "2")
        v.calU3.text = obtenerCalif(requireContext(), miVariable, "3")
        v.calU4.text = obtenerCalif(requireContext(), miVariable, "4")


        return v.root
    }
}
