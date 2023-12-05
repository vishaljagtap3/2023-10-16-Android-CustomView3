package com.bitcodetech.customview3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var files : ArrayList<File>
    private lateinit var recyclerFiles : RecyclerView
    private lateinit var filesAdapter: FilesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
        initDataSource()
        initAdapter()
        initListeners()

    }

    private fun initListeners() {
        filesAdapter.onFileClickListener = MyOnFileClickListener()
    }

    private inner class MyOnFileClickListener : FilesAdapter.OnFileClickListener {
        override fun onFileClick(position: Int, file: File, fileView: FileView) {
            fileView.takeAction()
        }
    }

    private fun mt(text : String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun initDataSource() {
        files = ArrayList<File>()
        files.addAll( Environment.getExternalStorageDirectory().listFiles())

        files.add(3, File("/storage/emulated/0/bitcode.png"))
        files.add(6, File("/storage/emulated/0/demo.pdf"))

    }

    private fun initAdapter() {
        filesAdapter = FilesAdapter(files)
        recyclerFiles.adapter = filesAdapter
    }

    private fun initViews() {
        setContentView(R.layout.activity_main)
        recyclerFiles = findViewById(R.id.recyclerFiles)
        recyclerFiles.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
    }
}