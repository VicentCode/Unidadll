package com.vicentcode.unidadll.Practicas.Fotos.ui

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.vicentcode.unidadll.Practicas.Fotos.Album
import com.vicentcode.unidadll.Practicas.Fotos.AlbumAdapter
import com.vicentcode.unidadll.Practicas.Fotos.AlbumSQL
import com.vicentcode.unidadll.databinding.FragmentAlbumVisorBinding

class AlbumVisor : Fragment() {
    private lateinit var v: FragmentAlbumVisorBinding
    private val PERMISSION_REQUEST_CODE = 1001

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = FragmentAlbumVisorBinding.inflate(inflater, container, false)
        return v.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //deleteAllData()
        if (checkPermission()) {
            v.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            v.recyclerView.adapter = AlbumAdapter(getAllInfo("SELECT * FROM albums"))
            v.recyclerView.setHasFixedSize(true)
        } else {
            // Si no se tienen los permisos, solicita permisos al usuario
            v.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            v.recyclerView.adapter = AlbumAdapter(getAllInfo("SELECT * FROM albums"))
            v.recyclerView.setHasFixedSize(true)
            requestPermission()
        }







    }


    private fun checkPermission(): Boolean {
        val permission = Manifest.permission.READ_EXTERNAL_STORAGE
        val result = ContextCompat.checkSelfPermission(requireContext(), permission)
        return result == PackageManager.PERMISSION_GRANTED
    }

    // Función para solicitar permisos al usuario
    private fun requestPermission() {
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
    }

    // Método que se ejecuta después de que el usuario responde a la solicitud de permisos
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // El usuario otorgó los permisos, realiza la operación de lectura de imágenes aquí
                // Ejecuta tu lógica para cargar imágenes o lo que necesites
            } else {
                // El usuario negó los permisos, puedes mostrar un mensaje o tomar alguna acción alternativa
            }
        }
    }

    fun getAllInfo(sql:String): MutableList<Album> {
        var adminSQLite: AlbumSQL? = null
        var bdconnector: SQLiteDatabase? = null
        adminSQLite = AlbumSQL(requireContext(), "fotitos", 2)
        bdconnector = adminSQLite!!.writableDatabase
        val datos = ContentValues()
        val albumList = mutableListOf<Album>()
        val cursor = bdconnector.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                albumList.add(
                    Album(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2)
                    )
                )
            } while (cursor.moveToNext())
        }
        bdconnector!!.close()
        return albumList
    }


    //create a fun to delete all data from table album
    fun deleteAllData(){
        val adminSQLite = AlbumSQL(requireContext(), "fotitos", 2)
        val bdconnector = adminSQLite.writableDatabase
        bdconnector.execSQL("DELETE FROM albums")
        bdconnector.close()
    }
}
