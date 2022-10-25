package com.example.karakiamobileapp.ui

import android.content.res.Configuration
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.LinearLayout
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.karakiamobileapp.MainActivity
import com.example.karakiamobileapp.R
import com.example.karakiamobileapp.databinding.FragmentKarakiaDetailsBinding


class KarakiaDetailsFragment : Fragment(R.layout.fragment_karakia_details) {

    //card views
    private lateinit var versesHiddenView: LinearLayout
    private lateinit var translationHiddenView: LinearLayout

    private val args by navArgs<KarakiaDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentKarakiaDetailsBinding.bind(view)
        val mediaControls = MediaController(activity)

        binding.apply {
            val karakia = args.karakia
            var videoUri = Uri.parse("android.resource://" + activity?.packageName + "/raw/" + karakia.videoResource)

            activity?.let { versesText.text = MainActivity.CustomClass(it.applicationContext).readTextFileToString(karakia.versesFileName)
                            translationText.text = MainActivity.CustomClass(it.applicationContext).readTextFileToString(karakia.englishFileName)
            }

            //setting video view
            karakiaVideo!!.setVideoURI(videoUri)
            karakiaVideo!!.setMediaController(mediaControls)
            mediaControls!!.setAnchorView(karakiaVideo)


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


        }


    }
}