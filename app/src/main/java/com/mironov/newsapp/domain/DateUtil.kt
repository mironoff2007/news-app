package com.mironov.newsapp.domain

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

object DateUtil {

    private const val PATTERN_DATE_FORMAT = "yyyy-MM-dd"
    private const val API_DATE_FORMAT = "yyyy-MM-dd"
    private const val UI_DATE_FORMAT = "dd MMM yyyy"

    fun getTodayDate(): String {
        val cal: Calendar = Calendar.getInstance()
        val year: Int = cal.get(Calendar.YEAR)
        val month: Int = cal.get(Calendar.MONTH)+1
        val day: Int = cal.get(Calendar.DAY_OF_MONTH)

        return  formatDate(day, month, year)
    }

    fun getPreviousDayDate(daysBack:Int): String {
        val cal: Calendar = Calendar.getInstance()
        cal.add(Calendar.DATE, -daysBack)
        val year: Int = cal.get(Calendar.YEAR)
        val month: Int = cal.get(Calendar.MONTH)+1
        val day: Int = cal.get(Calendar.DAY_OF_MONTH)

        return  formatDate(day, month, year)
    }

    @SuppressLint("SimpleDateFormat")
    fun formatDate(day: Int, month: Int, year: Int): String {
        val date = SimpleDateFormat(PATTERN_DATE_FORMAT).parse("$year-$month-$day")
        return SimpleDateFormat(API_DATE_FORMAT).format(date)
    }

    @SuppressLint("SimpleDateFormat")
    fun getDate(dateString: String?): String {
        return try {
            SimpleDateFormat(API_DATE_FORMAT).parse(dateString).toString()
        } catch (e: java.text.ParseException) {
            ""
        }
    }

    fun convertDate(date: String): String {
        val date = SimpleDateFormat(API_DATE_FORMAT).parse(date)
        return SimpleDateFormat(UI_DATE_FORMAT, Locale.getDefault()).format(date)
    }
}