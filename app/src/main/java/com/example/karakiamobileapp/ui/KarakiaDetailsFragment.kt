package com.example.karakiamobileapp.ui

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.karakiamobileapp.MainActivity
import com.example.karakiamobileapp.R
import com.example.karakiamobileapp.databinding.FragmentKarakiaDetailsBinding
import java.io.*


class KarakiaDetailsFragment : Fragment(R.layout.fragment_karakia_details) {

    //card views
    private lateinit var versesHiddenView: TextView
    private lateinit var translationHiddenView: TextView

    private val args by navArgs<KarakiaDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentKarakiaDetailsBinding.bind(view)

        binding.apply {
            val karakia = args.karakia
            var videoUri = Uri.parse("android.resource://" + activity?.packageName + "/raw/" + karakia.videoResource)

            activity?.let { versesText.text = MainActivity.CustomClass(it.applicationContext).readTextFileToString(karakia.verses.fileName)
                            translationText.text = MainActivity.CustomClass(it.applicationContext).readTextFileToString(karakia.english.fileName)
            }

            karakiaVideo.setVideoURI(videoUri)
            videoTitle.text = karakia.title

            versesHiddenView = view.findViewById(R.id.verses_text)

            versesButton.setOnClickListener {
                // If the CardView is already expanded, set its visibility
                // to gone and change the expand less icon to expand more.
                if (versesHiddenView.visibility == View.VISIBLE) {
                    // The transition of the hiddenView is carried out by the TransitionManager class.
                    // Here we use an object of the AutoTransition Class to create a default transition
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        TransitionManager.beginDelayedTransition(versesCardView, AutoTransition())
                    }
                    versesHiddenView.visibility = View.GONE
                    versesButton.setImageResource(R.drawable.expand_more)
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        TransitionManager.beginDelayedTransition(versesCardView, AutoTransition())
                    }
                    versesHiddenView.visibility = View.VISIBLE
                    versesButton.setImageResource(R.drawable.expand_more)
                }
            }

            translationHiddenView = view.findViewById(R.id.translation_text)

            translationButton.setOnClickListener {
                // If the CardView is already expanded, set its visibility
                // to gone and change the expand less icon to expand more.
                if (translationHiddenView.visibility == View.VISIBLE) {
                    // The transition of the hiddenView is carried out by the TransitionManager class.
                    // Here we use an object of the AutoTransition Class to create a default transition
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        TransitionManager.beginDelayedTransition(translationCardView, AutoTransition())
                    }
                    translationHiddenView.visibility = View.GONE
                    translationButton.setImageResource(R.drawable.expand_more)
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        TransitionManager.beginDelayedTransition(translationCardView, AutoTransition())
                    }
                    translationHiddenView.visibility = View.VISIBLE
                    translationButton.setImageResource(R.drawable.expand_more)
                }
            }


        }


    }


}