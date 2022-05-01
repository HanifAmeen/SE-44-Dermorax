package com.example.mobileapp

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.mobileapp.databinding.ActivityScheduleBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

class Schedule : AppCompatActivity() {
    private lateinit var binding:ActivityScheduleBinding
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnShowDateRangePicker.setOnClickListener {
            showDateRangepicker()
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun showDateRangepicker() {
        val dateRangePicker = MaterialDatePicker.Builder
            .dateRangePicker()
            .setTitleText("Select Date")
            .build()

        dateRangePicker.show(supportFragmentManager, "date_range_picker")
        dateRangePicker.addOnPositiveButtonClickListener { datePicked->
            val startDate = datePicked.first
            val endDate = datePicked.second

            if (startDate != null && endDate != null) {

                binding.tvDateRange.text =
                    "StartDate:" +convertLongToDate(startDate)+"\n"+ "EndDate:"+ convertLongToDate(endDate)

            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun convertLongToDate(time:Long):String{
        val date = Date(time)
        val format = SimpleDateFormat("dd-MM-yyy HH:mm", Locale.getDefault())
        return format.format(date)
    }
}