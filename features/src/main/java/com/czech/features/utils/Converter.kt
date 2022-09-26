package com.czech.features.utils

import android.content.ContentValues
import android.util.Log
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Converter {

    fun convertDateToYear(sourceDate: String?): String? {
        val sourceFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val targetFormat: DateFormat = SimpleDateFormat("yyyy", Locale.ENGLISH)
        if (sourceDate == null || sourceDate == "") return null
        var date: Date? = null
        try {
            date = sourceFormat.parse(sourceDate)
        } catch (e: ParseException) {
            e.printStackTrace()
            Log.e(ContentValues.TAG, "Error formatting the date")
        }
        return targetFormat.format(date)
    }
}