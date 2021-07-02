package com.example.nbateamsandplayers_testapp.presentation.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_domain.model.Team
import com.example.app_domain.repository.TeamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val repository: TeamRepository
): ViewModel() {

    private val teams: MutableLiveData<List<Team>> = MutableLiveData()

    fun getTeams(): LiveData<List<Team>> {
        loadTeams()
        return teams
    }

    private fun loadTeams() {
        viewModelScope.launch {
            val response = repository.getTeams()
            teams.postValue(response)
        }

    }

}