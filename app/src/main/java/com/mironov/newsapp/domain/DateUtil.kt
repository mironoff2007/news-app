package com.mironov.newsapp.domain

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

object DateUtil {

    private const val PATTERN_DATE_FORMAT = "yyyy-MM-dd"
    private const val API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm"
    private const val UI_DATE_FORMAT = "dd MMM yyyy"
    private const val UI_TIME_FORMAT = "HH:mm dd MMM"

    fun getTodayDate(): String {
        val cal: Calendar = Calendar.getInstance()
        val year: Int = cal.get(Calendar.YEAR)
        val month: Int = cal.get(Calendar.MONTH) + 1
        val day: Int = cal.get(Calendar.DAY_OF_MONTH)

        return  formatDate(day, month, year)
    }

    fun getPreviousDayDate(daysBack:Int): String {
        val cal: Calendar = Calendar.getInstance()
        cal.add(Calendar.DATE, -daysBack)
        val year: Int = cal.get(Calendar.YEAR)
        val month: Int = cal.get(Calendar.MONTH) + 1
        val day: Int = cal.get(Calendar.DAY_OF_MONTH)

        return  formatDate(day, month, year)
    }

    @SuppressLint("SimpleDateFormat")
    fun formatDate(day: Int, month: Int, year: Int): String {
        return try {
            val date = SimpleDateFormat(PATTERN_DATE_FORMAT).parse("$year-$month-$day")
            SimpleDateFormat(PATTERN_DATE_FORMAT).format(date)
        } catch (e: Exception) {
            ""
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getDate(dateString: String?): String {
        return try {
            SimpleDateFormat(PATTERN_DATE_FORMAT).parse(dateString ?: "")?.toString() ?: ""
        } catch (e: Exception) {
            ""
        }
    }

    fun convertDate(date: String): String {
        return try {
            val date = SimpleDateFormat(API_DATE_FORMAT).parse(date)
            SimpleDateFormat(UI_DATE_FORMAT, Locale.getDefault()).format(date)
        } catch (e: Exception) {
            ""
        }
    }

    fun convertTime(date: String): String {
        return try {
            val date = SimpleDateFormat(API_DATE_FORMAT).parse(date)
            SimpleDateFormat(UI_TIME_FORMAT, Locale.getDefault()).format(date)
        } catch (e: Exception) {
            ""
        }
    }
}