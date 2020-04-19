package me.abhishekraj.openmovie

import android.app.Activity
import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.crashreporter.CrashReporterPlugin
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.leakcanary.LeakCanaryFlipperPlugin
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
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

        SoLoader.init(this, false)

        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this)
            client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            client.addPlugin(NavigationFlipperPlugin.getInstance())
            val networkFlipperPlugin = NetworkFlipperPlugin()
            client.addPlugin(networkFlipperPlugin)
            client.addPlugin(DatabasesFlipperPlugin(this))
            client.addPlugin(
                SharedPreferencesFlipperPlugin(this, "my_shared_preference_file")
            )
            client.addPlugin(LeakCanaryFlipperPlugin())
            client.addPlugin(CrashReporterPlugin.getInstance())
            client.start()
        }
    }

    override fun activityInjector() = dispatchingAndroidInjector
}