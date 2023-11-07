package com.vicentcode.unidadll.Ejemplos.Ejemplo3SQL

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.vicentcode.unidadll.R
import com.vicentcode.unidadll.databinding.ActivitySqlBinding

class SQL : AppCompatActivity() {

    private lateinit var v: ActivitySqlBinding

    var adminSQLite: AdminSQLite? = null
    var bdconnector: SQLiteDatabase? = null
    var datos: ContentValues? = null
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        v = ActivitySqlBinding.inflate(layoutInflater)
        setContentView(v.root)
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item)
        adapter.add("Electronica")
        adapter.add("Abarrotes")
        adapter.add("Limpieza")
        adapter.add("Ropa")
        adapter.add("Herramientas")

        v.spCategoria.adapter = adapter
        adapter.notifyDataSetChanged()

        v.guardar.setOnClickListener {
            adminSQLite = AdminSQLite(this, "Tienda", 1)
            bdconnector = adminSQLite!!.writableDatabase

            datos = ContentValues()
            datos!!.put("codigo", v.etCodigo.text.toString())
            datos!!.put("nombre", v.etNombre.text.toString())
            datos!!.put("categoria", v.spCategoria.selectedItem.toString())
            datos!!.put("precio", v.etPrecio.text.toString())

            bdconnector!!.insert("productos", null, datos)
            Toast.makeText(this, "Producto guardado", Toast.LENGTH_SHORT).show()
            v.etNombre.setText("")
            v.spCategoria.setSelection(0)
            v.etPrecio.setText("")
            bdconnector!!.close()

        }

        v.actualizar.setOnClickListener {
            //Update selected data
            adminSQLite = AdminSQLite(this, "Tienda", 1)
            bdconnector = adminSQLite!!.writableDatabase
            //update data whit execSQL
            bdconnector!!.execSQL("update productos set nombre = '${v.etNombre.text.toString()}', categoria = '${v.spCategoria.selectedItem.toString()}', precio = '${v.etPrecio.text.toString()}' where codigo = '${v.etCodigo.text.toString()}'")
            bdconnector!!.close()

            //delete data whit execSQL
            //bdconnector!!.execSQL("delete from productos where codigo = '${v.etCodigo.text.toString()}'")

        }

        v.consultar.setOnClickListener {
            adminSQLite = AdminSQLite(this, "Tienda", 1)
            bdconnector = adminSQLite!!.readableDatabase

            val fila = bdconnector!!.rawQuery("select nombre, categoria, precio from productos where codigo = '${v.etCodigo.text.toString()}'", null)
            /*if (fila.moveToFirst()){
                v.etNombre.setText(fila.getString(0))
                v.spCategoria.setSelection(adapter.getPosition(fila.getString(1)))
                v.etPrecio.setText(fila.getString(2))
            }else{
                v.etNombre.setText("")
                v.spCategoria.setSelection(0)
                v.etPrecio.setText("")
            }

             */

            //show data in a alertDialog
            if (fila.moveToFirst()){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Datos del producto")
            builder.setMessage("Nombre: ${fila.getString(0)}\nCategoria: ${fila.getString(1)}\nPrecio: ${fila.getString(2)}")
            builder.setPositiveButton("Aceptar", null)
            val dialog: AlertDialog = builder.create()
            dialog.show()
            }else{
                v.etNombre.setText("")
                v.spCategoria.setSelection(0)
                v.etPrecio.setText("")
            }

            bdconnector!!.close()
        }
    }
}