package com.example.a5thclass_1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a5thclass_1.databinding.ItemDataBinding

class DataRVAdapter(private val dataList:ArrayList<Data>) : RecyclerView.Adapter<DataRVAdapter.DataViewHoler>() {

    //ViewHolder 객체
    inner class DataViewHoler(private val viewBinding : ItemDataBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data : Data) {
            viewBinding.textViewTitle.text = data.title
            viewBinding.textViewContent.text = data.desc
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    //viewType은 타입을 나누어 내가 보낸 메시지, 상대방이 보낸 메시지 등
    //상태를 나누는 것

    //ViewBInder 만들어질 때 실행할 동작
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHoler {
        //xml을 실제로 Binding에 넣어주는 작업
        val viewBinding = ItemDataBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DataViewHoler(viewBinding)
    }

    //ViewHoler가 실제로 데이터를 표시해야 할 때 호출되는 함수
    override fun onBindViewHolder(holder: DataViewHoler, position: Int) {
       //position : 몇번째 위치?
        holder.bind(dataList[position])
    }

    //표현할 item의 총 개수
    override fun getItemCount(): Int  = dataList.size
}