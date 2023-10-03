package com.vicentcode.unidadll.Ejemplos.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.vicentcode.unidadll.Ejemplos.datos
import com.vicentcode.unidadll.databinding.FragmentAzulBinding

class AzulFragment : Fragment() {

    private lateinit var v: FragmentAzulBinding

    var datos: Bundle? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            datos = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar la vista usando View Binding
        v = FragmentAzulBinding.inflate(inflater, container, false)

        // Retorna la vista raíz del binding
        return v.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       v.btnMsgAzul.setOnClickListener {
            Toast.makeText(context, datos!!.getString("msg"), Toast.LENGTH_SHORT).show()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AzulFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
