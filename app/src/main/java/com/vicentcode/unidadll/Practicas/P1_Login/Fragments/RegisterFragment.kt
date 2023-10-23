package com.vicentcode.unidadll.Practicas.P1_Login.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.vicentcode.unidadll.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private lateinit var v: FragmentRegisterBinding
    var userL: SharedPreferences.Editor?=null
    var userPass: SharedPreferences.Editor?=null
    var userBand: SharedPreferences.Editor?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar la vista usando View Binding
        v = FragmentRegisterBinding.inflate(inflater, container, false)

        // Retorna la vista raíz del binding
        return v.root
    }

    fun onBackPressedInFragmen() {
        if (isVisible) {
            requireView().isVisible = false

        }
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userLog = this.requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
        userL = userLog.edit()
        val ff = this.requireActivity().getSharedPreferences("pass", Context.MODE_PRIVATE)
        userPass = ff.edit()
        val dd = this.requireActivity().getSharedPreferences("band", Context.MODE_PRIVATE)
        userBand = dd.edit()
        v.backIC.setOnClickListener {
            requireView().isVisible = false
        }
        v.createACC.setOnClickListener {
            if (v.userNameED.text.toString() != "" && v.passED.text.toString() != "" && v.ConfirmPassED.text.toString() != ""){
                if (v.passED.text.toString() == v.ConfirmPassED.text.toString()){
                    userL?.putString("user", v.userNameED.text.toString())
                    userPass?.putString("pass", v.passED.text.toString())
                    userBand?.putBoolean("band", true)
                    userL?.commit()
                    userPass?.commit()
                    userBand?.commit()
                    Toast.makeText(this.requireContext(), "Cuenta: ${v.userNameED.text.toString()} creada con exito",
                        Toast
                        .LENGTH_SHORT).show()
                    requireView().isVisible = false
                }else{
                    v.ConfirmPassED.error = "Las contraseñas no coinciden"
                }
            }else{
                if (v.userNameED.text.toString() == ""){
                    v.userNameED.error = "Campo vacio"
                }
                if (v.passED.text.toString() == ""){
                    v.passED.error = "Campo vacio"
                }
                if (v.ConfirmPassED.text.toString() == ""){
                    v.ConfirmPassED.error = "Campo vacio"
                }
            }
        }

    }


}
