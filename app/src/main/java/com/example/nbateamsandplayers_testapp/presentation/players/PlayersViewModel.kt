package com.example.nbateamsandplayers_testapp.presentation.players

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_domain.model.Player
import com.example.app_domain.repository.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    private val repository: PlayerRepository
) : ViewModel() {

    private val players: MutableLiveData<List<Player>> = MutableLiveData()
    val filteredPlayers: MutableLiveData<List<Player>> = MutableLiveData()
    var nextPage: Int = 1 // Inizio da questa pagina alla creazione del fragment
    var nextFilteredPage: Int = 1

    init {
        loadPlayers()
    }

    fun getPlayers(): LiveData<List<Player>> {
        //loadPlayers()
        return players
    }

    fun flushPlayers() {
        players.postValue(mutableListOf<Player>())
    }

    fun loadPlayers() {
        viewModelScope.launch {
            val response = repository.getPlayers(nextPage)
            nextPage = response.keys.first()
            Log.d("******************LOAD", response.toString())

            val playersFromApi = response.get(nextPage).orEmpty()
            val newPlayerList = mutableListOf<Player>()

            if(playersFromApi != emptyList<Player>()) {
                newPlayerList.addAll(players.value.orEmpty())
                newPlayerList.addAll(playersFromApi)
            }
            players.postValue(newPlayerList)
        }

    }

    fun search(text: String, page: Int) {
        viewModelScope.launch {
            val response = repository.getFilteredPlayers(text, page)
            nextFilteredPage = response.keys.first()
            Log.d("*************SEARCH", response.toString())

            val playersFromApiRequest = response.get(nextFilteredPage).orEmpty()
            val newFilteredPlayerList = mutableListOf<Player>()

            if (playersFromApiRequest != emptyList<Player>()) {
                newFilteredPlayerList.addAll(filteredPlayers.value.orEmpty())
                newFilteredPlayerList.addAll(playersFromApiRequest)
            }
            filteredPlayers.postValue(newFilteredPlayerList)
            players.postValue(newFilteredPlayerList)
        }
    }


}