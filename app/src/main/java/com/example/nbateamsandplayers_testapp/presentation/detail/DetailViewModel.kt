package com.example.nbateamsandplayers_testapp.presentation.detail

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
class DetailViewModel @Inject constructor(
    private val repository: PlayerRepository
): ViewModel() {

    private val player: MutableLiveData<Player> = MutableLiveData()

    fun getPlayer(id: Int): LiveData<Player> {
        loadPlayer(id)
        return player
    }

    private fun loadPlayer(id: Int) {
        viewModelScope.launch {
            val response = repository.getPlayer(id)
            player.postValue(response)
        }
    }

}