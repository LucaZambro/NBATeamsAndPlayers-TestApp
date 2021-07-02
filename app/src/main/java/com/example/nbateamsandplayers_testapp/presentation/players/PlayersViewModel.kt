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
    private val filteredPlayersContainer: MutableList<Player> = mutableListOf()
    var nextPage: Int = 1 // Inizio da questa pagina alla creazione del fragment
    var nextFilteredPage: Int = 1

    init {
        loadPlayers()
    }

    fun getPlayers(): LiveData<List<Player>> {
        return players
    }

    fun loadPlayers() {
        viewModelScope.launch {
            val response = repository.getPlayers(1)
            nextPage = response.keys.first()
            Log.d("***************LOAD", response.toString())

            val playersFromApiRequest = response[nextPage].orEmpty()
            players.postValue(playersFromApiRequest)
        }

    }

    fun loadMorePlayers() {
        viewModelScope.launch {
            val response = repository.getPlayers(nextPage)
            nextPage = response.keys.first()
            Log.d("***************LOAD", response.toString())

            val playersFromApi = response[nextPage].orEmpty()

            val newPlayerList = mutableListOf<Player>()
            newPlayerList.addAll(players.value.orEmpty())
            newPlayerList.addAll(playersFromApi)

            players.postValue(newPlayerList)
        }

    }

    // Viene invocato dopo aver modificato la barra di ricerca
    fun search(text: String) {
        viewModelScope.launch {
            val response = repository.getFilteredPlayers(text, 1)
            nextFilteredPage = response.keys.first()
            Log.d("*************SEARCH", response.toString())

            val playersFromApiRequest = response[nextFilteredPage].orEmpty()

            filteredPlayersContainer.clear()
            filteredPlayersContainer.addAll(playersFromApiRequest)
            players.postValue(playersFromApiRequest)
        }
    }

    // Viene invocato allo scorrere della lista, se ci sono altri giocatori da mostrare
    fun searchMorePlayers(text: String) {
        viewModelScope.launch {
            val response = repository.getFilteredPlayers(text, nextFilteredPage)
            nextFilteredPage = response.keys.first()
            Log.d("*********SEARCHMORE", response.toString())

            val playersFromApiRequest = response[nextFilteredPage].orEmpty()

            filteredPlayersContainer.addAll(playersFromApiRequest)
            players.postValue(filteredPlayersContainer)
        }
    }


}