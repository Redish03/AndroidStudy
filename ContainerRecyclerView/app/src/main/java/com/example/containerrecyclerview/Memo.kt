package com.example.containerrecyclerview

data class Memo(
    val no: Int,
    val title: String,
    val timeStamp: Long,
    val onMemoClick: (position: Int) -> Unit,
//    객체 생성시 Memo 클래스가 가지고 있을 함수를 지정해준다. 이 함수의 이름은 onMemoClick이다.
)