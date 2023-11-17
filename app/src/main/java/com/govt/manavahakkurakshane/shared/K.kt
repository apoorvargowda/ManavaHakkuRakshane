package com.manasa.myapplication.shared

import android.app.Activity
import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import java.util.Locale


class K {
    companion object {
        private val calendar = Calendar.getInstance()
        //----calender fun------
        fun show_calender(activity: Activity, textdata: TextView) {
            val datePickerDialog =DatePickerDialog(activity,{DatePicker, year: Int, month0fYear:Int, date0fYear:Int ->
                val selectedDate = Calendar. getInstance()
                selectedDate.set (year, month0fYear, date0fYear)
                val dateFormat = SimpleDateFormat ("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format (selectedDate.time)
                textdata.text = formattedDate
            },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
            )
            datePickerDialog.show()
            //return textdata
        }

    }
}