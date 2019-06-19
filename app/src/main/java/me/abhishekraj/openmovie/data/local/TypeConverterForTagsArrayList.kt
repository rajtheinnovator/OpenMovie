package me.abhishekraj.openmovie.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import me.abhishekraj.openmovie.data.model.Movie

/**
 * Created by Abhishek Raj on 6/19/2019.
 */

class TypeConverterForMovieArrayList {

    companion object {

        @TypeConverter
        @JvmStatic
        fun arrayListToString(listToBeConverted: ArrayList<Movie>?): String? {
            if (listToBeConverted != null) {
                val gson = Gson()
                return gson.toJson(listToBeConverted)
            } else return "No Movie"
        }

        @TypeConverter
        @JvmStatic
        fun stringToArrayList(stringToBeConverted: String?): ArrayList<Movie>? {
            if (stringToBeConverted.equals("No Movie")) {
                return null
            } else {
                val listType = object : TypeToken<ArrayList<Movie>>() {
                }.type
                return Gson().fromJson(stringToBeConverted, listType)
            }
        }
    }
}