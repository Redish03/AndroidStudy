package com.example.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.activity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val activityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == RESULT_OK) {
                val message = it.data?.getStringExtra("returnValue")
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        }
        val intent = Intent(this, SubActivity::class.java)
        intent.putExtra("from1", "Hello Bundle")
        intent.putExtra("from2", 2023)

        binding.btnStart.setOnClickListener { activityResult.launch(intent) }
    }
}
