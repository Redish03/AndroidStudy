package com.example.containerrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.containerrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val customAdapter: CustomAdapter by lazy {
        CustomAdapter()
    }
    private var itemList = mutableListOf<Memo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = customAdapter
        }

        // 초깃값 설정. 1~10 까지 아이템을 넣어줌
        loadData()
        customAdapter.submitList(itemList)
        binding.addButton.setOnClickListener {
            addItem()
        }
    }

//    5트, 람다식 적용
//
//        customAdapter.setItemClickListener(object : CustomAdapter.OnItemClickListener {
//            override fun OnClick(v: View, position: Int) {
//                4트, data class copy 적용

//                3트, 성공
//                itemList[position] = createNewMemo(itemList[position].no, "Touch Event Detected", System.currentTimeMillis())
//                customAdapter.submitList(itemList)

//                1트
//                itemList[position].title = "Touch event detected"
//                customAdapter.submitList(itemList.toList())

//                2트
//                val newItemList = itemList.toList()
//                newItemList[position].title = "Touch event detected"
//                Log.d("newItemList", newItemList[position].title)
//                customAdapter.submitList(newItemList)
//                Log.d("submitList working", "not working")
//            }
//        })
//    }

    private fun addItem() {
        val newItem = createNewMemo()
        itemList.add(newItem)
        customAdapter.submitList(itemList.toList())
    }

    private fun createNewMemo(): Memo {
        val number = itemList.count() + 1
        val title = "이것이 안드로이드다 ${itemList.count() + 1}"
        val date = System.currentTimeMillis()
//        lambda 적용
        return Memo(number, title, date, ::updatePosition)
    }

//    onMemoClick이 실행 되었을 때 어떤 함수를 실행 시킬지 람다식으로 지정 가능.
//    updatePosition을 람다로 제공. updatePosition이 실행된다.
    private fun updatePosition(position: Int) {
        itemList[position] = itemList[position].copy(title = "fixed Title")
        customAdapter.submitList(itemList.toList())
    }

    //    private fun createNewMemo(num: Int, title: String, date: Long): Memo = Memo(num, title, date)
    private fun loadData() {
        for (no in 1..10) {
            itemList.add(createNewMemo())
        }
    }
}