package com.vicentcode.unidadll.Practicas.Fotos

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vicentcode.unidadll.R
data class Album(val photoName: String, val albumName: String, val imageURI: ByteArray)

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



            itemView.findViewById<TextView>(R.id.fotoName).text = "Nombre: " + album.photoName
            itemView.findViewById<TextView>(R.id.albumName).text = "Album: " + album.albumName
            val imagev = itemView.findViewById<ImageView>(R.id.ivFoto)

            val imageUri = album.imageURI // Supongo que "imageURI" es el campo que contiene el URI de la imagen
            // en
            // la base de datos
            val bitmap = BitmapFactory.decodeByteArray(album.imageURI, 0, album.imageURI.size)
            imagev.setImageBitmap(bitmap)


        }
    }
}
