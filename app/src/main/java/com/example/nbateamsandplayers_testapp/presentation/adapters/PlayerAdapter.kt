package com.example.nbateamsandplayers_testapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_domain.model.Player
import com.example.nbateamsandplayers_testapp.databinding.PlayerCardItemBinding

class PlayerAdapter() : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    var dataSet: List<Player> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onPlayerClickListener: ((player: Player) -> Unit)? = null

    inner class ViewHolder(val binding: PlayerCardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(player: Player) {

            binding.apply {
                txtPlayerName.text = player.firstName.uppercase()
                txtPlayerSurname.text = player.lastName.uppercase()

                root.setOnClickListener {
                    onPlayerClickListener?.invoke(player)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            PlayerCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size


}