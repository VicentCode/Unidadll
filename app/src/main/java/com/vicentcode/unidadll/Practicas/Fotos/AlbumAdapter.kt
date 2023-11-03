package com.vicentcode.unidadll.Practicas.Fotos

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vicentcode.unidadll.R
data class Album(val photoName: String, val albumName: String, val imageURI: String)

class AlbumAdapter(private val albumList: List<Album>) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albumList[position]
        holder.bind(album)
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(album: Album) {
            //show and add the information from table to the recycler view from sqlit



            itemView.findViewById<TextView>(R.id.fotoName).text = album.photoName
            itemView.findViewById<TextView>(R.id.albumName).text = album.imageURI
            val imagev = itemView.findViewById<ImageView>(R.id.ivFoto)

            Log.d("AlbumAdapter", "bind: ${album.imageURI}")
            //Picasso.get().load(album.imageURI.toUri()).into(itemView.findViewById<ImageView>(R.id.ivFoto))
            imagev.setImageURI(album.imageURI.toUri())


        }
    }
}
