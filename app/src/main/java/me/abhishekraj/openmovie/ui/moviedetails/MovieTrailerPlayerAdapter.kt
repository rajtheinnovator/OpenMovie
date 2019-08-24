package me.abhishekraj.openmovie.ui.moviedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import me.abhishekraj.openmovie.R
import me.abhishekraj.openmovie.data.model.VideosResult

/**
 * Created by Abhishek Raj on 6/26/2019.
 */

class MovieTrailerPlayerAdapter(
    var videoIds: ArrayList<VideosResult>?,
    private val lifecycle: Lifecycle,
    private val trailerClicked: TrailerClicked?,
    private val selectedTrailer: VideosResult?
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
        viewHolder.cueVideo(videoIds?.get(position)!!, trailerClicked, selectedTrailer)

    }

    override fun getItemCount(): Int {
        return videoIds?.size!!
    }

    class ViewHolder(private var youTubePlayerView: YouTubePlayerView) :
        RecyclerView.ViewHolder(youTubePlayerView) {
        private var currentVideoId: String? = null

        fun cueVideo(
            videoId: VideosResult,
            trailerClicked: TrailerClicked?,
            selectedTrailer: VideosResult?
        ) {
            currentVideoId = videoId.key

            youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(selectedTrailer?.key!!, 0f)
                }
            })

            youTubePlayerView.addYouTubePlayerListener(object : YouTubePlayerListener {
                override fun onApiChange(youTubePlayer: YouTubePlayer) {

                }

                override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                }

                override fun onError(
                    youTubePlayer: YouTubePlayer,
                    error: PlayerConstants.PlayerError
                ) {
                }

                override fun onPlaybackQualityChange(
                    youTubePlayer: YouTubePlayer,
                    playbackQuality: PlayerConstants.PlaybackQuality
                ) {
                }

                override fun onPlaybackRateChange(
                    youTubePlayer: YouTubePlayer,
                    playbackRate: PlayerConstants.PlaybackRate
                ) {
                }

                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.cueVideo(currentVideoId!!, 0f)
                }

                override fun onStateChange(
                    youTubePlayer: YouTubePlayer,
                    state: PlayerConstants.PlayerState
                ) {
                    if (state.equals(PlayerConstants.PlayerState.PLAYING)) {
                        trailerClicked?.onTrailerCued(videoId.name!!)
                    }
                }

                override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {
                }

                override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {
                }

                override fun onVideoLoadedFraction(
                    youTubePlayer: YouTubePlayer,
                    loadedFraction: Float
                ) {
                }
            })
        }
    }

    interface TrailerClicked {
        fun onTrailerCued(name: String)
    }
}
