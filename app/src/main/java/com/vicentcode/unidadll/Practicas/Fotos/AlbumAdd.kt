package com.vicentcode.unidadll.Practicas.Fotos

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.vicentcode.unidadll.Ejemplos.Ejemplo3SQL.AdminSQLite
import com.vicentcode.unidadll.R
import com.vicentcode.unidadll.databinding.FragmentAlbumAddBinding
import java.io.ByteArrayOutputStream
import java.io.IOException

class AlbumAdd : Fragment() {
    private lateinit var v: FragmentAlbumAddBinding
    var adminSQLite: AlbumSQL? = null
    var bdconnector: SQLiteDatabase? = null
    var imageUri: Any? = null
    val dataList = ArrayList<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = FragmentAlbumAddBinding.inflate(inflater, container, false)
        return v.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val editText = v.edAlbum
        adminSQLite = AlbumSQL(requireContext(), "fotitos", 2)
        bdconnector = adminSQLite!!.writableDatabase
        dataList.clear()
        val cursor = bdconnector!!.rawQuery("SELECT DISTINCT albumName FROM albums", null)

        if (cursor.moveToFirst()) {
            do {
                dataList.add(cursor.getString(0))
            } while (cursor.moveToNext())
        }
        bdconnector!!.close()
        val autoComple: AutoCompleteTextView = v.userTpyeAU
        val adapters = ArrayAdapter(requireContext(), R.layout.list_item, dataList)
        autoComple.setAdapter(adapters)


        v.button4.setOnClickListener {
            if (imageUri != null) {
                if (v.userTpyeAU.text.toString().isNotEmpty() && v.edFoto.text.toString().isNotEmpty()) {

                    adminSQLite = AlbumSQL(requireContext(), "fotitos", 2)
                    bdconnector = adminSQLite!!.writableDatabase

                    val datos = ContentValues()
                    datos.put("photoName", v.edFoto.text.toString())
                    datos.put("albumName", v.userTpyeAU.text.toString())
                    val imageBytes = imageUri?.let { uri ->
                        try {
                            val inputStream = requireContext().contentResolver.openInputStream(uri as Uri)
                            val byteArrayOutputStream = ByteArrayOutputStream()
                            val buffer = ByteArray(4096)
                            var bytesRead: Int
                            while (inputStream!!.read(buffer).also { bytesRead = it } != -1) {
                                byteArrayOutputStream.write(buffer, 0, bytesRead)
                            }
                            byteArrayOutputStream.toByteArray()
                        } catch (e: IOException) {
                            null
                        }
                    }

                    if (imageBytes != null) {
                        datos.put("imageURI", imageBytes)
                        Log.d("AlbumAdd", "onViewCreated: ${imageBytes}")

                        val result = bdconnector!!.insert("albums", null, datos)
                        if (result != -1L) {
                            Toast.makeText(requireContext(), "Álbum guardado", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireContext(), "Error al guardar el álbum", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(requireContext(), "Error al obtener la imagen", Toast.LENGTH_SHORT).show()
                    }

                    bdconnector!!.close()
                } else {
                    v.listItem.error = "Ingrese un nombre de album"
                    v.ILFoto.error = "Ingrese un nombre de foto"
                }
            } else {
                Toast.makeText(requireContext(), "Selecciona una imagen", Toast.LENGTH_SHORT).show()
            }


        }

        v.imageView3.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }



        v.fabSave.setOnClickListener {
            if (v.edAlbum.text!!.isNotEmpty()) {
                val newItem = editText.text.toString()
                if (newItem.isNotEmpty()) {
                    UpdateSpinner(dataList, true, newItem)

                    editText.text!!.clear()
                }
            } else {
                v.ILAlbum.error = "Ingrese un nombre de album"
            }
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data

            if (imageUri != null) {
                Picasso.get().load(imageUri as Uri).into(v.imageView3)
                Toast.makeText(requireContext(), imageUri.toString(), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Selecciona una imagen", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //when the fragment on resume clear and update the spinner
    override fun onResume() {
        super.onResume()
        v.userTpyeAU.text!!.clear()
        UpdateSpinner(dataList, false, "")
    }


    fun UpdateSpinner(data: ArrayList<String>, addNewItem: Boolean, itemData: String): ArrayList<String> {
        // add new item to bd and update spinner
        if (addNewItem) {
            adminSQLite = AlbumSQL(requireContext(), "fotitos", 2)
            bdconnector = adminSQLite!!.writableDatabase
            val datos = ContentValues()
            datos.put("albumName", itemData)
            val result = bdconnector!!.insert("albums", null, datos)
            if (result != -1L) {
                Toast.makeText(requireContext(), "Álbum guardado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Error al guardar el álbum", Toast.LENGTH_SHORT).show()
            }
            bdconnector!!.close()
        }

        adminSQLite = AlbumSQL(requireContext(), "fotitos", 2)
        bdconnector = adminSQLite!!.writableDatabase
        data.clear()
        val cursor = bdconnector!!.rawQuery("SELECT DISTINCT albumName FROM albums", null)

        if (cursor.moveToFirst()) {
            do {
                data.add(cursor.getString(0))
            } while (cursor.moveToNext())
        }
        bdconnector!!.close()
        val autoComple: AutoCompleteTextView = v.userTpyeAU
        val adapters = ArrayAdapter(requireContext(), R.layout.list_item, data)
        autoComple.setAdapter(adapters)

        return data
    }


}


