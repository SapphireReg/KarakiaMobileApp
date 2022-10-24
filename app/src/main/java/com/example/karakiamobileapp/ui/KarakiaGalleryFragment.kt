package com.example.karakiamobileapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.karakiamobileapp.R
import com.example.karakiamobileapp.data.Karakia
import com.example.karakiamobileapp.databinding.FragmentKarakiaGalleryBinding
import com.example.karakiamobileapp.util.exhaustive
import com.example.karakiamobileapp.util.onQueryTextChanged
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class KarakiaGalleryFragment : Fragment(R.layout.fragment_karakia_gallery), KarakiaAdapter.OnItemClickListener {

    private val viewModel by viewModels<KarakiaGalleryViewModel>()
    private var binding: FragmentKarakiaGalleryBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
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
                        val action =
                            KarakiaGalleryFragmentDirections.actionKarakiaGalleryFragmentToKarakiaDetailsFragment(
                                event.karakia
                            )
                        findNavController().navigate(action)
                    }
                }.exhaustive
            }
        }

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.gallery_menu, menu)

                val searchItem = menu.findItem(R.id.action_search)
                val searchView = searchItem.actionView as SearchView

                val pendingQuery = viewModel.currentQuery.value
                if (pendingQuery != null && pendingQuery.isNotEmpty()) {
                    searchItem.expandActionView() //expands magnifying icon
                    searchView.setQuery(pendingQuery, false)
                }

                searchView.onQueryTextChanged {
                    viewModel.currentQuery.value = it
                }

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


    override fun onItemClick(karakia: Karakia) {
        viewModel.onKarakiaSelected(karakia)
    }



    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }



}