package com.example.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import com.example.timer.databinding.ActivityMainBinding

class MainActivity :AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var timer: CountDownTimer? = null
    private var countTime: Long = 45000
    private val TAG = "TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button2.visibility = View.INVISIBLE
        initButton()
    }
    private fun Timer(){
       timer =  object : CountDownTimer(countTime, 1000) {
            override fun onTick(p0: Long) {
            Log.d(TAG, "$countTime")
                binding.textView.text = "${p0/ 1000}"
                countTime = p0
            }
            override fun onFinish() {
                binding.textView.text = "Finish"
            }
        }.start()
    }
    private fun initButton(){
        binding.button.setOnClickListener {
            Timer()
            binding.button.visibility = View.INVISIBLE
            binding.button3.visibility = View.VISIBLE
            binding.button2.visibility = View.VISIBLE
        }
        binding.button3.setOnClickListener {
            timer?.cancel()
            Log.d(TAG,"$countTime")
            binding.button3.visibility = View.INVISIBLE
            binding.button.visibility = View.VISIBLE
            binding.button2.visibility = View.VISIBLE
                // binding.button.text = " Возобновить"
        }
        binding.button2.setOnClickListener {
            countTime = 0
            binding.textView.text = "00"
            binding.button3.visibility = View.INVISIBLE
            binding.button.visibility = View.VISIBLE
            timer?.cancel()
        }

    }
}