package me.abhishekraj.openmovie.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.abhishekraj.openmovie.ui.moviedetails.MovieDetailsFragment
import me.abhishekraj.openmovie.ui.movielist.PopularMoviesFragment
import me.abhishekraj.openmovie.ui.movielist.TopRatedMoviesFragment

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributePopularMovieFragment(): PopularMoviesFragment

    @ContributesAndroidInjector
    abstract fun contributeTopRatedMovieFragment(): TopRatedMoviesFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailsFragment(): MovieDetailsFragment
}