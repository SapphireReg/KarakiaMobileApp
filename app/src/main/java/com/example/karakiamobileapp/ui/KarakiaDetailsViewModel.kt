package com.example.karakiamobileapp.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KarakiaDetailsViewModel @Inject constructor(
    private val state: SavedStateHandle
) : ViewModel() {

}