package com.vicentcode.unidadll.Practicas.Fotos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vicentcode.unidadll.databinding.ActivityMenuBinding

class ActivityMenu : AppCompatActivity() {
    private lateinit var v: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        v = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(v.root)


    }
}