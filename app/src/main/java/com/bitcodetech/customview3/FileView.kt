package com.bitcodetech.customview3

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import java.io.File

class FileView(
    context: Context,
    attributeSet: AttributeSet?
) : LinearLayout(context, attributeSet){

    private lateinit var imgFile : ImageView
    private lateinit var txtFileName : TextView
    private lateinit var txtFileMetaData : TextView

    constructor(context: Context) : this(context, null)

    init {
        this.addView(
            LayoutInflater.from(context).inflate(R.layout.file_view, null)
        )
        imgFile = findViewById(R.id.imgFile)
        txtFileName  = findViewById(R.id.txtFileName)
        txtFileMetaData = findViewById(R.id.txtFileMetaData)
    }

    var file : File? = null
        set(value) {
            field = value

            when {
                value!!.isDirectory -> {
                    imgFile.setImageResource(R.drawable.folder)
                    txtFileMetaData.setText("${value.listFiles().size}")
                }
                value!!.name.endsWith("pdf") ->{
                    imgFile.setImageResource(R.drawable.pdf)
                    txtFileMetaData.text = "Size: ${value.length()}"
                }
                value!!.name.endsWith("png") -> {
                    imgFile.setImageResource(R.drawable.image)
                    txtFileMetaData.text = "Size: ${value.length()}"
                }
            }

            txtFileName.setText(value!!.name)
        }

    fun takeAction() {
        when {
            file!!.isDirectory -> mt("Open the directory")
            file!!.name.endsWith("png") -> mt("Display image")
            file!!.name.endsWith("pdf") -> mt("Open pdf file")
            file!!.name.endsWith("mp3") -> mt("Play audio file")
            file!!.name.endsWith("mp4") -> mt("Play video file")
        }
    }

    private fun mt(text : String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}