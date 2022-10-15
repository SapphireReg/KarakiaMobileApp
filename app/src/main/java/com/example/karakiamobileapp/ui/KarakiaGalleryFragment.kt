package com.example.karakiamobileapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.karakiamobileapp.R
import com.example.karakiamobileapp.data.Karakia
import com.example.karakiamobileapp.databinding.FragmentKarakiaGalleryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class KarakiaGalleryFragment : Fragment(R.layout.fragment_karakia), KarakiaAdapter.OnItemClickListener {

    private val viewModel: KarakiaGalleryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentKarakiaGalleryBinding.bind(view)

        val karakiaAdapter = KarakiaAdapter(this)

        binding.apply {
            recyclerView.apply {
                adapter = karakiaAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        viewModel.karakias.observe(viewLifecycleOwner) {
            karakiaAdapter.submitList(it) // updates adapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.karakiaEvent.collect { event ->
                when (event) {
                    is KarakiaGalleryViewModel.KarakiaEvent.NavigateToFragmentKarakiaDetails -> {
                        val action = KarakiaGalleryFragmentDirections.actionKarakiaGalleryFragmentToKarakiaDetailsFragment()
                        findNavController().navigate(action)
                    }
                }
            }
        }

    }



    override fun onItemClick(karakia: Karakia) {
        viewModel.onKarakiaSelected(karakia)
    }
}