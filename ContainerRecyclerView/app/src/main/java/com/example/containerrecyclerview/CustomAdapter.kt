package com.example.containerrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.containerrecyclerview.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class CustomAdapter : ListAdapter<Memo, Holder>(diffUtil) {
    //    5트, 람다식 적용, interface 삭제
//    private lateinit var itemClickListener: OnItemClickListener
//    interface OnItemClickListener{
//        fun OnClick(v: View, position: Int)
//    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding)
    }

//    override fun submitList() {
//
//    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
//        holder.itemView.setOnClickListener{
//            itemClickListener.OnClick(it, position)
//        }
        holder.setMemo(getItem(position))
    }

//    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
//        this.itemClickListener = onItemClickListener
//    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Memo>() {
            override fun areItemsTheSame(oldItem: Memo, newItem: Memo): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Memo, newItem: Memo): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class CustomAdapter2(memos: List<Memo> = listOf()) : RecyclerView.Adapter<Holder>() {
    //    방어적 복사
    private val _memos = memos.toMutableList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setMemo(_memos[position])
    }

    override fun getItemCount(): Int = _memos.size

    fun submitList(memos: List<Memo>) {
        _memos.clear()
        _memos.addAll(memos)
        notifyDataSetChanged()
    }
}

class Holder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setMemo(memo: Memo) {
        binding.textNo.text = "${memo.no}"
        binding.textTitle.text = memo.title
//        #5트
//        item의 Constraint layout을 클릭했을 때, MainActivity에서 지정한 onMemoClick을 실행
        binding.lyitem.setOnClickListener {
            memo.onMemoClick(adapterPosition)
        }
        val sdf = SimpleDateFormat("yyyy/MM/dd")
        val formattedDate = sdf.format(memo.timeStamp)
        binding.textDate.text = formattedDate
    }
}