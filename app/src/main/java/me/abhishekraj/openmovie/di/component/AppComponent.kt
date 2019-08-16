package me.abhishekraj.openmovie.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import me.abhishekraj.openmovie.OpenMovieApp
import me.abhishekraj.openmovie.di.MainActivityModule
import me.abhishekraj.openmovie.di.module.AppModule
import javax.inject.Singleton

/**
 * Created by Abhishek Raj on 8/15/2019.
 */

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(openMovieApp: OpenMovieApp)
}