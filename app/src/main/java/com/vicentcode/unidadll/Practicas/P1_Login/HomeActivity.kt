package com.vicentcode.unidadll.Practicas.P1_Login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.vicentcode.unidadll.R
import com.vicentcode.unidadll.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var v: ActivityHomeBinding
    var userL: SharedPreferences.Editor?=null
    var userPass: SharedPreferences.Editor?=null
    var userBand: SharedPreferences.Editor?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        v = ActivityHomeBinding.inflate(layoutInflater)
        val view = v.root
        setContentView(view)
        val userLog = this.getSharedPreferences("user", Context.MODE_PRIVATE)
        userL = userLog.edit()
        val ff = this.getSharedPreferences("pass", Context.MODE_PRIVATE)
        userPass = ff.edit()
        val dd = this.getSharedPreferences("band", Context.MODE_PRIVATE)
        userBand = dd.edit()

        v.user.text = "Bienvenido: ${userLog.getString("user", "null")}"
        Toast.makeText(this, "Bienvenido de nuevo: ${userLog.getString("user", "null")}", Toast.LENGTH_SHORT).show()

        v.logOut.setOnClickListener {
            userBand?.putBoolean("band", false)
            userBand?.commit()
            val intent = Intent(this@HomeActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}