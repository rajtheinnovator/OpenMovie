package me.abhishekraj.openmovie.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.abhishekraj.openmovie.data.model.Movie

/**
 * Created by Abhishek Raj on 6/19/2019.
 */

@Database(entities = arrayOf(Movie::class), version = 1, exportSchema = false)
@TypeConverters(TypeConverterForMovieArrayList::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        private val LOG_TAG = AppDatabase::class.java.simpleName
        private val LOCK = Any()
        private val DATABASE_NAME = "moviedatabase.db"
        private var sInstance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (sInstance == null) {
                synchronized(LOCK) {
                    sInstance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        AppDatabase::class.java, AppDatabase.DATABASE_NAME
                    )
                        .build()
                }
            }
            return sInstance!!
        }
    }
}
