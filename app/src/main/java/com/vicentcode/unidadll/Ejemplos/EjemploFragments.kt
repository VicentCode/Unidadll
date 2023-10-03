package com.vicentcode.unidadll.Ejemplos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import androidx.fragment.app.Fragment
import com.vicentcode.unidadll.Ejemplos.Fragments.AzulFragment
import com.vicentcode.unidadll.Ejemplos.Fragments.OrangeFragment
import com.vicentcode.unidadll.R
import com.vicentcode.unidadll.databinding.ActivityEjemploFragmentsBinding

var datos = Bundle()
private lateinit var v: ActivityEjemploFragmentsBinding
class EjemploFragments : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        v = ActivityEjemploFragmentsBinding.inflate(layoutInflater)
        val view = v.root
        setContentView(view)
        v.btnBlue.setOnClickListener {
                loadFragment(AzulFragment(), "Blue parsed from Activity")
        }

        v.btnOrange.setOnClickListener {
            loadFragment(OrangeFragment(), "Orange parsed from Activity ggg")
        }
    }

    private fun loadFragment(fragment: Fragment, message: String ) {

        val fragmentTransactionn = supportFragmentManager.beginTransaction()
        datos.putString("msg", message)
        fragment.arguments = datos
        fragmentTransactionn.add(R.id.frame, fragment)
        fragmentTransactionn.commit()
    }
}
