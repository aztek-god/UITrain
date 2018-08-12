package com.dv.serg.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dv.serg.ui.R


class SimpleAdapter(private val imageUrlList: List<String>) : RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_view_layout, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageUrlList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imageUrlList[position])
    }


    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val mImageView: ImageView = view.findViewById(R.id.thumb)

        fun bind(url: String) {
            Glide.with(mImageView).load(url).into(mImageView)
        }
    }
}