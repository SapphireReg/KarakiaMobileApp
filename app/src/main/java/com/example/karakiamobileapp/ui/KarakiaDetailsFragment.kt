package com.example.karakiamobileapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.karakiamobileapp.MainActivity
import com.example.karakiamobileapp.R
import com.example.karakiamobileapp.databinding.FragmentKarakiaDetailsBinding
import java.util.*
import kotlin.concurrent.schedule


class KarakiaDetailsFragment : Fragment(R.layout.fragment_karakia_details) {

    //card views
    private lateinit var versesHiddenView: LinearLayout
    private lateinit var translationHiddenView: LinearLayout

    private val args by navArgs<KarakiaDetailsFragmentArgs>()

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentKarakiaDetailsBinding.bind(view)
        val mediaControls = MediaController(activity)

        binding.apply {
            val karakia = args.karakia
            val videoUri = Uri.parse("android.resource://" + activity?.packageName + "/raw/" + karakia.videoResource)

            activity?.let { versesText.text = MainActivity.CustomClass(it.applicationContext).readTextFileToString(karakia.versesFileName)
                            translationText.text = MainActivity.CustomClass(it.applicationContext).readTextFileToString(karakia.englishFileName)
            }

            if (karakia.videoResource!=null) {
                //setting video view
                karakiaVideo.setVideoURI(videoUri)
                karakiaVideo.setMediaController(mediaControls)
                mediaControls.setAnchorView(karakiaVideo)
            } else {
                Toast.makeText(context, "Video doesn't exist", Toast.LENGTH_SHORT).show()
            }
            longDescription.text = karakia.longDescription
            videoTitle.text = karakia.title
            versesHiddenView = view.findViewById(R.id.hidden_verses)

            versesButton.setOnClickListener {
                // If the CardView is already expanded, set its visibility
                // to gone and change the expand less icon to expand more.
                if (versesHiddenView.visibility == View.VISIBLE) {
                    // The transition of the hiddenView is carried out by the TransitionManager class.
                    // Here we use an object of the AutoTransition Class to create a default transition
                    TransitionManager.beginDelayedTransition(versesCardView, AutoTransition())
                    versesHiddenView.visibility = View.GONE
                    versesButton.setImageResource(R.drawable.expand_more)
                } else {
                    TransitionManager.beginDelayedTransition(versesCardView, AutoTransition())
                    versesHiddenView.visibility = View.VISIBLE
                    versesButton.setImageResource(R.drawable.expand_more)
                }
            }

            translationHiddenView = view.findViewById(R.id.hidden_translation)

            translationButton.setOnClickListener {
                // If the CardView is already expanded, set its visibility
                // to gone and change the expand less icon to expand more.
                if (translationHiddenView.visibility == View.VISIBLE) {
                    // The transition of the hiddenView is carried out by the TransitionManager class.
                    // Here we use an object of the AutoTransition Class to create a default transition
                    TransitionManager.beginDelayedTransition(translationCardView, AutoTransition())
                    translationHiddenView.visibility = View.GONE
                    translationButton.setImageResource(R.drawable.expand_more)
                } else {
                    TransitionManager.beginDelayedTransition(translationCardView, AutoTransition())
                    translationHiddenView.visibility = View.VISIBLE
                    translationButton.setImageResource(R.drawable.expand_more)
                }
            }

            var videoContainer: RelativeLayout = view.findViewById(R.id.video_view_parent)

            //show actionbar on touch
            videoContainer.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
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
        } //binding end
    } //onViewCreated end

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        var videoView: VideoView = requireView().findViewById(R.id.karakia_video)
        var karakiaInfoContainer: RelativeLayout = requireView().findViewById(R.id.karakia_info_container)

        //fullscreen on landscape
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ) {
            videoView?.layoutParams!!.height = LinearLayout.LayoutParams.MATCH_PARENT
            videoView.start()
            karakiaInfoContainer.visibility = GONE
            (activity as AppCompatActivity).supportActionBar?.hide()
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            videoView?.layoutParams!!.height = pxToDp(200)
            karakiaInfoContainer.visibility = VISIBLE
        }
    } //onConfigurationChanged end

    fun pxToDp(px: Int): Int { //convert px to dp
        val density = resources.displayMetrics.density

        return Math.round(density * px)
    }

}