package com.vicentcode.unidadll.Ejemplos.Ejemplo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.vicentcode.unidadll.Ejemplos.Ejemplo1.Fragments.AzulFragment
import com.vicentcode.unidadll.Ejemplos.Ejemplo1.Fragments.OrangeFragment
import com.vicentcode.unidadll.R
import com.vicentcode.unidadll.databinding.ActivityEjemploFragmentsBinding

var datos = Bundle()
var img = Bundle()
var band = "f"
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

        v.chip.setOnClickListener {
            band = "chip1"
        }
        v.chip2.setOnClickListener {
            band = "chip2"
        }
        v.chipOrange.setOnClickListener{
            band = "chip11"
        }
        v.chip2Orange.setOnClickListener{
            band = "chip22"
        }
    }

    private fun loadFragment(fragment: Fragment, message: String ) {

        val fragmentTransactionn = supportFragmentManager.beginTransaction()
        datos.putString("msg", message)

        if (band == "chip1"){
            img.putInt("imagen", R.drawable.img1)
            fragment.arguments = img
        }else if (band == "chip2"){
            img.putInt("imagen", R.drawable.img2)
            fragment.arguments = img
        }else if (band == "chip11"){
            img.putInt("imagen", R.drawable.img11)
            fragment.arguments = img
        }else if (band == "chip22"){
            img.putInt("imagen", R.drawable.img22)
            fragment.arguments = img
        }
        val combinedBundle = Bundle()
        combinedBundle.putAll(datos)
        combinedBundle.putAll(img)

// Asignar el Bundle combinado al fragmento
        fragment.arguments = combinedBundle
        fragmentTransactionn.add(R.id.frame, fragment)
        fragmentTransactionn.commit()
    }
}
