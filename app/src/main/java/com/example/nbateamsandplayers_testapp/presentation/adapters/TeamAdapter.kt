package com.example.nbateamsandplayers_testapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_domain.model.Team
import com.example.nbateamsandplayers_testapp.databinding.TeamCardItemBinding

class TeamAdapter : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    var dataSet: List<Team> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: TeamCardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(team: Team) {

            binding.apply {
                txtAbbreviation.text = team.abbreviation
                txtFullName.text = team.fullName.uppercase()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            TeamCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

}