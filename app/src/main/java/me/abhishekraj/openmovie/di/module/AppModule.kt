package me.abhishekraj.openmovie.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import me.abhishekraj.openmovie.data.local.AppDatabase
import me.abhishekraj.openmovie.data.local.MovieDao
import me.abhishekraj.openmovie.data.remote.MovieDbService
import me.abhishekraj.openmovie.util.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Abhishek Raj on 8/15/2019.
 */

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideGithubService(): MovieDbService {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(MovieDbService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, "moviedatabase.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(db: AppDatabase): MovieDao {
        return db.movieDao()
    }
}