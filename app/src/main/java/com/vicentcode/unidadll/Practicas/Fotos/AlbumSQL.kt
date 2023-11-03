package com.vicentcode.unidadll.Practicas.Fotos

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class AlbumSQL(context: Context, bdName: String, version: Int?): SQLiteOpenHelper(context, bdName, null, version!!) {
    override fun onCreate(p0: SQLiteDatabase?) {
       //create album table for albums, (id, fotoName, albumName, imageURI)
        p0?.execSQL("CREATE TABLE albums (photoName TEXT, albumName TEXT, imageURI TEXT)")

        //p0?.execSQL("create table albums( fotoName text, albumName text, imageURI text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}