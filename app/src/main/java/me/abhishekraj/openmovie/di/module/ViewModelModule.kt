package me.abhishekraj.openmovie.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.abhishekraj.openmovie.di.ViewModelKey
import me.abhishekraj.openmovie.ui.moviedetails.MovieDetailsViewModel
import me.abhishekraj.openmovie.ui.movielist.MovieListViewModel
import me.abhishekraj.openmovie.viewmodel.OpenMovieViewModelFactory

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: MovieListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    abstract fun bindRepoViewModel(repoViewModel: MovieDetailsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: OpenMovieViewModelFactory): ViewModelProvider.Factory
}
