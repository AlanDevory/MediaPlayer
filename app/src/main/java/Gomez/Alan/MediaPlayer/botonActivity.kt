package Gomez.Alan.MediaPlayer

import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.random.Random

class botonActivity : AppCompatActivity() {

    lateinit var  musicaFondo:MediaPlayer
    lateinit var  respuestaUsuario:EditText
    lateinit var btnRespuesta:Button
    lateinit var  sonidoRespuestaCorrecta:MediaPlayer
    lateinit var  sonidoRespuestaIncorrecta:MediaPlayer
    var numeroGenerado = 0
    var numeroUsuario = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boton)
        initUI()

        musicaFondo = MediaPlayer.create(this, R.raw.atmosfera)
        sonidoRespuestaCorrecta = MediaPlayer.create(this,R.raw.acertaste)
        sonidoRespuestaIncorrecta = MediaPlayer.create(this,R.raw.error)
        reproduceMusica()
        generaNumero()
        
        btnRespuesta.setOnClickListener { 
            val respuesta = respuestaUsuario.text.toString()
            if(respuesta.equals("")){
                Toast.makeText(this, "Ingresa un valor", Toast.LENGTH_LONG).show()
                sonidoIncorrecto()
            }else{
                numeroUsuario = respuesta.toInt()
                if(numeroGenerado == numeroUsuario){
                    sonidoCorrecto()

                }else{
                    sonidoIncorrecto()
                    Toast.makeText(this, "El valor era $numeroGenerado", Toast.LENGTH_SHORT).show()

                }
                generaNumero()
            }
        }


    }

    override fun onStop() {
        super.onStop()
        musicaFondo.release()
        sonidoRespuestaCorrecta.release()
        sonidoRespuestaIncorrecta.release()
    }
    fun reproduceMusica(){
        musicaFondo.isLooping = true
        musicaFondo.setVolume(0.5f,0.5f)
        musicaFondo.start() // no need to call prepare(); create() does that for you
    }
    fun sonidoCorrecto(){
        sonidoRespuestaCorrecta.start()


    }
    fun sonidoIncorrecto(){
        sonidoRespuestaIncorrecta.start()
    }
    fun initUI(){
        btnRespuesta = findViewById(R.id.btnComprobar)
        respuestaUsuario = findViewById(R.id.etEntradaUsuario)
    }

    fun generaNumero(){
        numeroGenerado = Random.nextInt(1,10)
    }


}