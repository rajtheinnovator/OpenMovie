package me.abhishekraj.openmovie.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.abhishekraj.openmovie.ui.MainActivity

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}