package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var listFragment: ListFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFragment()
        binding.btnSend.setOnClickListener {
            listFragment.setValue("Will you marry me?")
        }
    }

    fun goDetail() {
        val detailFragment = DetailFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainerView, detailFragment)
        transaction.addToBackStack("detail")
        transaction.commit()
    }

    fun goBack() {
        onBackPressed()
    }

    fun setFragment() {
        listFragment = ListFragment()

        val bundle = Bundle()
        bundle.putString("key1", "Hello World")
        bundle.putInt("key2", 20240417)
        listFragment.arguments = bundle

        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.fragmentContainerView, listFragment)
        transaction.commit()
    }
}
