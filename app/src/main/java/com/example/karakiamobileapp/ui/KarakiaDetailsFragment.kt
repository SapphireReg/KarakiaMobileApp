package com.example.karakiamobileapp.ui

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
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

        binding.apply {
            val karakia = args.karakia

            karakiaVideo.setVideoURI(
                Uri.parse("android.resource://"
                    + activity?.packageName + "/" + karakiaVideo))
            videoTitle.text = karakia.title
            versesText.text = karakia.verses.toString()
            translationText.text = karakia.english.toString()

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