package com.vicentcode.unidadll.Practicas.P2.Logins

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.vicentcode.unidadll.R
import com.vicentcode.unidadll.databinding.FragmentRegisterMateriasBinding

class Register : Fragment() {

    private lateinit var v: FragmentRegisterMateriasBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = FragmentRegisterMateriasBinding.inflate(inflater, container, false)

        val sharedPreferences = requireContext().getSharedPreferences("user", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        v.backIC.setOnClickListener {
            requireView().isVisible = false
        }


        v.createACC.setOnClickListener {
            val nombre = v.userNameED.text.toString()
            val pass = v.passED.text.toString()

            if (v.ConfirmPassED.text.toString() != pass){
                v.ConfirmPassED.error = "Contraseña no coincide"
            }else {
                if (nombre.isEmpty()) {
                    v.userNameED.error = "Campo vacio"
                } else if (pass.isEmpty()) {
                    v.passED.error = "Campo vacio"
                } else {
                    editor.putString("username", nombre)
                    editor.putString("password", pass)
                    editor.commit()
                    Toast.makeText(requireContext(), "Usuario $nombre creado", Toast.LENGTH_SHORT).show()
                    requireView().isVisible = false
                }
            }

        }

        return v.root
    }



}
