package com.vicentcode.unidadll.Ejemplos.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.vicentcode.unidadll.databinding.FragmentOrangeBinding

class OrangeFragment : Fragment() {

    private lateinit var v: FragmentOrangeBinding
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
        v = FragmentOrangeBinding.inflate(inflater, container, false)
        return v.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        v.btnMsgOrange.setOnClickListener {
            Toast.makeText(context, datos!!.getString("msg"), Toast.LENGTH_SHORT).show()
        }

    }
/*
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OrangeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
          }
 */

}
