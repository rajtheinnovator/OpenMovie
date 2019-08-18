package me.abhishekraj.openmovie.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.abhishekraj.openmovie.ui.moviedetails.MovieDetailsFragment
import me.abhishekraj.openmovie.ui.movielist.MovieListFragment

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment(): MovieListFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailsFragment(): MovieDetailsFragment
}