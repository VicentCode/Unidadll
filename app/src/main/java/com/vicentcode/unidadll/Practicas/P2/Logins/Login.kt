package com.vicentcode.unidadll.Practicas.P2.Logins

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.vicentcode.unidadll.Practicas.P1_Login.HomeActivity
import com.vicentcode.unidadll.Practicas.P2.Menu.Home
import com.vicentcode.unidadll.databinding.FragmentLoginMateriasBinding

class Login : Fragment() {

    private lateinit var v: FragmentLoginMateriasBinding
    var userL: SharedPreferences.Editor?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = FragmentLoginMateriasBinding.inflate(inflater, container, false)

        val sharedPreferences = requireContext().getSharedPreferences("user", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        v.register.setOnClickListener {
            val fragment = Register()
            requireActivity().supportFragmentManager.beginTransaction().add(com.vicentcode.unidadll.R.id.frameMaterias,
                fragment)
                .commit()
        }

        v.login.setOnClickListener {

            val user = sharedPreferences.getString("username", "null")
            val pass = sharedPreferences.getString("password", "null")

            if (v.userNameED.text.toString() == user && v.passED.text.toString() == pass){
                editor.putBoolean("session", true)
                editor.commit()
                val intent = Intent(activity, Home::class.java)
                startActivity(intent)

            }else{
                v.userNameLayout.error = "Usuario o contraseña incorrectos"
                v.passLayout.error = "Usuario o contraseña incorrectos"
            }
        }

        return v.root
    }
}
