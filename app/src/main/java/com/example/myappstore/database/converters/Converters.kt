package com.example.testdefinelabs.database.converters

import androidx.room.TypeConverter
import com.example.myappstore.database.Images
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    companion object {

        @TypeConverter
        @JvmStatic
        fun fromList(list: List<Images>): String {


            val gson = Gson()
            val typeConverter = object : TypeToken<List<Images>>() {}.type

            val str = gson.toJson(list, typeConverter)
            return str
        }


        @TypeConverter
        @JvmStatic
        fun toList(stringList: String): List<Images> {

            val gson = Gson()
            val typeConverter = object : TypeToken<List<Images>>() {}.type

            val list = gson.fromJson<List<Images>>(stringList, typeConverter)

            return list
        }
    }
}