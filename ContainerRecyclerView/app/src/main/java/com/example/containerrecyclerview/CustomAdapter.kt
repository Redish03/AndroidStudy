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

class CustomAdapter: ListAdapter<Memo, Holder>(diffUtil) {
    private lateinit var itemClickListener: OnItemClickListener
    interface OnItemClickListener{
        fun OnClick(v: View, position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding)
    }

//    override fun submitList() {
//
//    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener{
            itemClickListener.OnClick(it, position)
        }
        holder.setMemo(getItem(position))
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

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
class Holder(val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root) {
    fun setMemo(memo: Memo) {
        binding.textNo.text = "${memo.no}"
        binding.textTitle.text = memo.title

        var sdf = SimpleDateFormat("yyyy/MM/dd")
        var formattedDate = sdf.format(memo.timeStamp)
        binding.textDate.text = formattedDate
    }
}