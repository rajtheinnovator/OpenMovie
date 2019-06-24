package me.abhishekraj.openmovie.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import me.abhishekraj.openmovie.R
import me.abhishekraj.openmovie.ui.movielist.MovieListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.transaction {
                add(R.id.fl_fragment_container, MovieListFragment(), "MovieListFragment")
            }
        }
    }
}
