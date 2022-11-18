package com.example.a6thclass_3

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a6thclass_3.databinding.ItemRadioImageBinding

//이미지 슬라이드를 만들 Adapter
class RadioAVAdapter(private val dataList : ArrayList <Int>) : RecyclerView.Adapter<RadioAVAdapter.ImageViewHolder>() {
    inner class ImageViewHolder(private val viewBinding : ItemRadioImageBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: Int) {
            viewBinding.imageSlide.setImageResource(data)
            //Glide라는 외부라이브러리 Glide써서 해보시길..
            Log.d("fun bind","ruuning")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioAVAdapter.ImageViewHolder {
        val viewBinding = ItemRadioImageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ImageViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: RadioAVAdapter.ImageViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

}