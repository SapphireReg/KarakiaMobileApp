package com.example.karakiamobileapp.ui

import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.karakiamobileapp.R

class GettingStartedFragment : Fragment (R.layout.fragment_getting_started) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mediaControls = MediaController(activity)
        val videoPlayer: VideoView  = view.findViewById(R.id.video_player)

        val videoUri = Uri.parse("android.resource://" + activity?.packageName + "/raw/" + R.raw.getting_started_video)


        //show actionbar on touch
        videoPlayer.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            val actionBar = (activity as AppCompatActivity).supportActionBar
            when (motionEvent.action) {
                MotionEvent.ACTION_MOVE, MotionEvent.ACTION_DOWN, MotionEvent.ACTION_UP -> {
                    val orientation = requireActivity().getResources().configuration.orientation
                    if ((orientation == Configuration.ORIENTATION_LANDSCAPE) || !(actionBar?.isShowing)!!) {
                        actionBar?.show()
                    }
                }
            }
            return@OnTouchListener true
        })
        videoPlayer.setVideoURI(videoUri)
        videoPlayer.start()
        videoPlayer.setMediaController(mediaControls)
        mediaControls.setAnchorView(videoPlayer)
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        val videoView: VideoView = requireView().findViewById(R.id.video_player)

        //fullscreen on landscape
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ) {
            videoView.layoutParams!!.height = LinearLayout.LayoutParams.MATCH_PARENT
            videoView.start()
            (activity as AppCompatActivity).supportActionBar?.hide()
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            videoView.layoutParams!!.height = pxToDp(200)
        }
    } //onConfigurationChanged end

    fun pxToDp(px: Int): Int { //convert px to dp
        val density = resources.displayMetrics.density

        return Math.round(density * px)
    }

}