package com.vicentcode.unidadll.Ejemplos.Ejemplo3SQL

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQLite(context: Context, bdName: String, version: Int?): SQLiteOpenHelper(context, bdName, null, version!!) {
    override fun onCreate(p0: SQLiteDatabase?) {
        //create table for users
        /*
        p0?.execSQL("create table usuarios(id integer primary key autoincrement, nombre text, apellido text, password text)")
        p0?.execSQL("create table materias(nombre text primary key)")
        p0?.execSQL("create table unidades(id integer primary key autoincrement, calificacion integer, id_materia text, foreign key(id_materia) references materias(nombre))")


         */
        //create table for productos con codigo, nombre, categoria con spinner y precio
        p0?.execSQL("create table productos(codigo text, nombre text, categoria text, precio text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}