package com.bitcodetech.customview3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class FilesAdapter(
    private val files : ArrayList<File>
) : RecyclerView.Adapter<FilesAdapter.FileViewHolder>() {

    interface OnFileClickListener {
        fun onFileClick(position : Int, file : File, view : FileView)
    }

    var onFileClickListener : OnFileClickListener? = null

    inner class FileViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val fileView : FileView
        init {
            fileView = view as FileView

            view.setOnClickListener {
                onFileClickListener?.onFileClick(
                    adapterPosition,
                    files[adapterPosition],
                    it as FileView
                )
            }
        }


    }

    override fun getItemCount() = files.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        return FileViewHolder(
            FileView(parent.context)
        )
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.fileView.file = files[position]
    }
}