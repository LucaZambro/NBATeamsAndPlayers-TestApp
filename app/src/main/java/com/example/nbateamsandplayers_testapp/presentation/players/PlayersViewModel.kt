package com.example.nbateamsandplayers_testapp.presentation.players

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
): ViewModel() {

    private val players: MutableLiveData<List<Player>> = MutableLiveData()
    private var nextPage: Int = 1 // Inizio dalla prima pagina alla creazione del fragment

    fun getPlayers(): LiveData<List<Player>> {
        loadPlayers()
        return players
    }

    fun loadPlayers() {
        viewModelScope.launch {
            val response = repository.getPlayers(nextPage)
            nextPage = response.keys.first()

            val newPlayerList = mutableListOf<Player>()
            newPlayerList.addAll(players.value.orEmpty())
            newPlayerList.addAll(response.get(nextPage)!!)
            players.postValue(newPlayerList)
        }

    }

    fun search(text: String) {
        /*
        // RICERCA DEI GIOCATORI GIà CARICATI
        viewModelScope.launch {
            val response = repository.getPlayers().filter {
                it.firstName.contains(text, ignoreCase = true) || it.lastName.contains(text, ignoreCase = true)
            }
            players.postValue(response)
        }
        */

        // RICERCA DEI GIOCATORI CON API
        viewModelScope.launch {
            val response = repository.getFilteredPlayers(text)
            players.postValue(response)
        }
    }

}