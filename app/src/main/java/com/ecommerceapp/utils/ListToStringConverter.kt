package com.ecommerceapp.utils

import androidx.room.TypeConverter

object ListToStringConverter {
    @TypeConverter
    fun toString(stringList: List<String>): String {
        return "("+stringList.joinToString(separator = ",")+")"
    }
}