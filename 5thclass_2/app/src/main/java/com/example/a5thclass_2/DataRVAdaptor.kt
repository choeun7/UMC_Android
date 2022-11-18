package com.example.a5thclass_2

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.a5thclass_2.databinding.ItemDataBinding

class DataRVAdaptor (private val datalist : ArrayList<MemoData>) : RecyclerView.Adapter<DataRVAdaptor.DataViewHolder>() {

    //ViewHolder 객체
    inner class DataViewHolder(private val viewBinding : ItemDataBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        private var pos : Int = 0
        fun bind(data : MemoData, position: Int) {
            viewBinding.itemTitle.text = data.title
            viewBinding.itemContent.text = data.content
            viewBinding.itemDeleteBtn.setOnClickListener {
                datalist.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }

    //RecycleView 재사용 오류 방지 코드
    override fun getItemViewType(position: Int): Int {
        return position
    }

    //ViewBinder 만들어질 때 실행할 동작
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = ItemDataBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DataViewHolder(viewBinding)
    }

    //ViewHolder가 실제로 데이터를 표시해야 할 때 호출되는 함수
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(datalist[position], position)
        holder.itemView.setOnClickListener {
            datalist.removeAt(position)
            notifyItemRemoved(position)
        }

    }

    //표현할 item의 총 개수
    override fun getItemCount(): Int = datalist.size

    fun removeItem(position : Int) {
        datalist.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }

}
