package com.example.a5thclass_3

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a5thclass_3.databinding.ItemDataBinding

class DataRVAdaptor (private val dataList : ArrayList<MemoData>) : RecyclerView.Adapter<DataRVAdaptor.DataViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(view : View, data : MemoData, position : Int)
    }
    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    //ViewHolder 객체
    inner class DataViewHolder(private var viewBinding : ItemDataBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(data: MemoData, position: Int) {
            viewBinding.itemTitle.text = data.title
            viewBinding.itemContent.text = data.content

            viewBinding.itemDeleteBtn.setOnClickListener {
                dataList.removeAt(position)
                notifyItemRemoved(position)
            }
        }
//        init {
//            viewBinding.item.setOnClickListener(View.OnClickListener {
//                val pos = absoluteAdapterPosition
//                Log.d("position", "$pos")
//                dataList.removeAt(pos)
//                notifyItemRemoved(pos)
//            })
//        }
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
        holder.bind(dataList[position], position)

        if(position != RecyclerView.NO_POSITION) {
            holder.itemView.setOnClickListener {
                listener?.onItemClick(holder.itemView, dataList[position], position)
            }
        }
//        holder.itemView.setOnClickListener {
//            val intent = Intent(holder.itemView?.context, AddMemoActivity::class.java)
//            intent.putExtra("title", dataList[position].title)
//            intent.putExtra("content",dataList[position].content)
//            ContextCompat.startActivity(holder.itemView.context, intent, null)
//        }
    }

    //표현할 item의 총 개수
    override fun getItemCount(): Int = dataList.size

}