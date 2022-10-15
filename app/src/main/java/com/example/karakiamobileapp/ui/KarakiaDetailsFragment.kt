package com.example.karakiamobileapp.ui

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.karakiamobileapp.R
import com.example.karakiamobileapp.databinding.FragmentKarakiaDetailsBinding

class KarakiaDetailsFragment : Fragment(R.layout.fragment_karakia_details) {

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
        }
    }


}