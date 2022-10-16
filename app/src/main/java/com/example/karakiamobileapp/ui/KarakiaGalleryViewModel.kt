package com.example.karakiamobileapp.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.karakiamobileapp.MainActivity
import com.example.karakiamobileapp.data.Karakia
import com.example.karakiamobileapp.data.KarakiaDao
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class KarakiaGalleryViewModel @ViewModelInject constructor(
    private val karakiaDao: KarakiaDao,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {

    private val karakiaEventChannel = Channel<KarakiaEvent>()
    val karakiaEvent = karakiaEventChannel.receiveAsFlow()

    val searchQuery = state.getLiveData("searchQuery", "")

    private val karakiasFlow = searchQuery.asFlow().flatMapLatest { query -> karakiaDao.getKarakiaByTitle(query) }

    val karakias = karakiasFlow.asLiveData() //get karakias from database

    fun onKarakiaSelected(karakia: Karakia) = viewModelScope.launch {
        karakiaEventChannel.send(KarakiaEvent.NavigateToFragmentKarakiaDetails(karakia))
    }

    //enum but can store data, lists of events
    sealed class KarakiaEvent {
        data class NavigateToFragmentKarakiaDetails(val karakia: Karakia) : KarakiaEvent()
    }


}