package com.example.karakiamobileapp.ui

import androidx.lifecycle.*
import com.example.karakiamobileapp.MainActivity
import com.example.karakiamobileapp.data.Karakia
import com.example.karakiamobileapp.data.KarakiaDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KarakiaGalleryViewModel @Inject constructor(
    private val karakiaDao: KarakiaDao,
    private val state: SavedStateHandle
) : ViewModel() {

    private val karakiaEventChannel = Channel<KarakiaEvent>()
    val karakiaEvent = karakiaEventChannel.receiveAsFlow()

    val currentQuery = state.getLiveData("CURRENT_QUERY", "")

    private val karakiasFlow = currentQuery.asFlow().flatMapLatest { query -> karakiaDao.getKarakiaByTitle(query) }

    val karakias = karakiasFlow.asLiveData() //get karakias from database

    fun onKarakiaSelected(karakia: Karakia) = viewModelScope.launch {
        karakiaEventChannel.send(KarakiaEvent.NavigateToFragmentKarakiaDetails(karakia))
    }

    //enum but can store data, lists of events
    sealed class KarakiaEvent {
        data class NavigateToFragmentKarakiaDetails(val karakia: Karakia) : KarakiaEvent()
    }

    fun searchPhotos(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val CURRENT_QUERY = "current_query"
    }



}