package com.vicentcode.unidadll.Practicas.P1_Login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.vicentcode.unidadll.Ejemplos.Ejemplo1.band
import com.vicentcode.unidadll.Ejemplos.Ejemplo1.datos
import com.vicentcode.unidadll.Ejemplos.Ejemplo1.img
import com.vicentcode.unidadll.Ejemplos.Ejemplo2.ArchPrefs2
import com.vicentcode.unidadll.Practicas.P1_Login.Fragments.RegisterFragment
import com.vicentcode.unidadll.R
import com.vicentcode.unidadll.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    var userL: SharedPreferences.Editor?=null
    var userPass: SharedPreferences.Editor?=null
    var userBand: SharedPreferences.Editor?=null
    private lateinit var v: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        v = ActivityLoginBinding.inflate(layoutInflater)
        val view = v.root
        setContentView(view)

        val userLog = this.getSharedPreferences("user", Context.MODE_PRIVATE)
        userL = userLog.edit()
        val ff = this.getSharedPreferences("pass", Context.MODE_PRIVATE)
        userPass = ff.edit()
        val dd = this.getSharedPreferences("band", Context.MODE_PRIVATE)
        userBand = dd.edit()

        v.register.setOnClickListener {
            v.frameRegister.isVisible = true
            loadFragment(RegisterFragment())
        }

        if (dd.getBoolean("band", false)){
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        v.login.setOnClickListener {
            if (v.userNameED.text.toString() == userLog.getString("user", "null") && v.passED.text.toString() == ff.getString("pass", "null")){
                userBand?.putBoolean("band", true)
                userBand?.commit()
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }

        }


    }
    private fun loadFragment(fragment: Fragment) {

        val fragmentTransactionn = supportFragmentManager.beginTransaction()
        fragmentTransactionn.setCustomAnimations(R.anim.slide_in_right, 0, 0, 0)
        fragmentTransactionn.add(R.id.frameRegister, fragment)
        fragmentTransactionn.commit()
    }


    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.frameRegister)

        if (fragment is RegisterFragment) {
            // Llama a la función en el fragmento para manejar el botón de retroceso
            fragment.onBackPressedInFragmen()
        } else {
            super.onBackPressed()
        }
    }
}