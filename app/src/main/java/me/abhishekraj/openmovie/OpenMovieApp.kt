package me.abhishekraj.openmovie

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import me.abhishekraj.openmovie.di.AppInjector
import javax.inject.Inject

/**
 * Created by Abhishek Raj on 8/16/2019.
 */

class OpenMovieApp : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            // Timber.plant(Timber.DebugTree())
        }
        AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}