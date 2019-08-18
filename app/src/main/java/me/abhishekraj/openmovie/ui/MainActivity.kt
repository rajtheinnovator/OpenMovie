package me.abhishekraj.openmovie.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import me.abhishekraj.openmovie.R
import me.abhishekraj.openmovie.ui.movielist.MovieListFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.transaction {
                add(R.id.fl_fragment_container, MovieListFragment(), "MovieListFragment")
            }
        }
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}
