package me.abhishekraj.openmovie.ui.moviedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import me.abhishekraj.openmovie.R
import me.abhishekraj.openmovie.data.model.VideosResult

/**
 * Created by Abhishek Raj on 6/26/2019.
 */

class MovieTrailerPlayerAdapter(
    var videoIds: ArrayList<VideosResult>?,
    private val lifecycle: Lifecycle,
    private val trailerClicked: TrailerClicked?
) : RecyclerView.Adapter<MovieTrailerPlayerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_play_movie_trailer, parent, false)

        val youTubePlayerView = view.findViewById<YouTubePlayerView>(R.id.youtube_player_view)
        lifecycle.addObserver(youTubePlayerView)

        return ViewHolder(youTubePlayerView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.cueVideo(videoIds?.get(position)!!, trailerClicked)

    }

    override fun getItemCount(): Int {
        return videoIds?.size!!
    }

    class ViewHolder(private val youTubePlayerView: YouTubePlayerView) :
        RecyclerView.ViewHolder(youTubePlayerView) {
        private var youTubePlayer: YouTubePlayer? = null
        private var currentVideoId: String? = null

        init {

            youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    this@ViewHolder.youTubePlayer = youTubePlayer
                    this@ViewHolder.youTubePlayer!!.cueVideo(currentVideoId!!, 0f)
                }
            })
        }

        fun cueVideo(
            videoId: VideosResult,
            trailerClicked: TrailerClicked?
        ) {
            currentVideoId = videoId.key

            if (youTubePlayer == null)
                return

            youTubePlayer!!.cueVideo(videoId.key!!, 0f)
            trailerClicked?.onTrailerCued(videoId.name ?: "Trailers")

        }
    }

    interface TrailerClicked {
        fun onTrailerCued(name: String)
    }
}
