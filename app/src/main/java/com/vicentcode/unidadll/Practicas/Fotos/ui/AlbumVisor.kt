package com.vicentcode.unidadll.Practicas.Fotos.ui

import android.Manifest
import android.R
import android.content.ContentValues
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
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


        val spinner = v.filtersp
        val adminSQLite = AlbumSQL(requireContext(), "fotitos", 2)
        val bdconnector = adminSQLite.writableDatabase
        val cursor = bdconnector.rawQuery("SELECT DISTINCT albumName FROM albums", null)
        val dataList = ArrayList<String>()
        dataList.add("Todos")
        if (cursor.moveToFirst()) {
            do {
                dataList.add(cursor.getString(0))
            } while (cursor.moveToNext())
        }
        bdconnector.close()
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, dataList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position != 0) {
                    v.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    v.recyclerView.adapter = AlbumAdapter(getAllInfo("SELECT * FROM albums WHERE albumName = '${dataList[position]}'"))
                    v.recyclerView.setHasFixedSize(true)
                } else {
                    v.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    v.recyclerView.adapter = AlbumAdapter(getAllInfo("SELECT * FROM albums"))
                    v.recyclerView.setHasFixedSize(true)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                v.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                v.recyclerView.adapter = AlbumAdapter(getAllInfo("SELECT * FROM albums"))
                v.recyclerView.setHasFixedSize(true)

            }

        }

        //verify if recicleview is empty


    }

    fun getAllInfo(sql:String): MutableList<Album> {
        var adminSQLite: AlbumSQL? = null
        var bdconnector: SQLiteDatabase? = null
        adminSQLite = AlbumSQL(requireContext(), "fotitos", 2)
        bdconnector = adminSQLite!!.writableDatabase
        val datos = ContentValues()
        val albumList = mutableListOf<Album>()
        val cursor = bdconnector.rawQuery(sql, null)
        try {
            if (cursor.moveToFirst()) {
                do {
                    val col0 = cursor.getString(0)
                    val col1 = cursor.getString(1)
                    val col2 = cursor.getBlob(2)

                    if (col0 != null && col1 != null && col2 != null) {
                        albumList.add(Album(col0, col1, col2))
                    }
                } while (cursor.moveToNext())
            }
            bdconnector!!.close()
        } catch (e: Exception) {
            // Manejar la excepción, si es necesario
            Log.e("Error", e.message.toString())


    }

        if ( albumList.isEmpty()) {
            v.recyclerView.visibility = View.INVISIBLE
            v.empty.visibility = View.VISIBLE
            v.textEmpty.visibility = View.VISIBLE
        } else {
            v.recyclerView.visibility = View.VISIBLE
            // v.emptyView.visibility = View.GONE
            v.empty.visibility = View.INVISIBLE
            v.textEmpty.visibility = View.INVISIBLE
        }
        return albumList
    }

    fun deleteAllData(){
        val adminSQLite = AlbumSQL(requireContext(), "fotitos", 2)
        val bdconnector = adminSQLite.writableDatabase
        bdconnector.execSQL("DELETE FROM albums")
        bdconnector.close()
    }
}
