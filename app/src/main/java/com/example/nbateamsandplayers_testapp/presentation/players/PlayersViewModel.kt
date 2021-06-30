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

    fun getPlayers(): LiveData<List<Player>> {
        loadPlayers()
        return players
    }

    fun loadPlayers() {
        viewModelScope.launch {
            val response = repository.getPlayers()
            players.postValue(response)
        }

    }

    fun search(text: String) {
        viewModelScope.launch {
            val response = repository.getPlayers().filter {
                it.firstName.contains(text, ignoreCase = true) || it.lastName.contains(text, ignoreCase = true)
            }
            players.postValue(response)
        }
    }
}