package com.example.nbateamsandplayers_testapp.presentation.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbateamsandplayers_testapp.R
import com.example.nbateamsandplayers_testapp.databinding.DetailFragmentBinding
import com.example.nbateamsandplayers_testapp.databinding.PlayersFragmentBinding
import com.example.nbateamsandplayers_testapp.presentation.adapters.PlayerAdapter
import com.example.nbateamsandplayers_testapp.presentation.players.PlayersFragmentDirections
import com.example.nbateamsandplayers_testapp.presentation.players.PlayersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: DetailFragmentBinding
    val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPlayer(args.playerId).observe(viewLifecycleOwner, { player ->
            binding.apply {
                txtPlayerId.text = player.id.toString()
                txtPlayerName.text = player.firstName.uppercase()
                txtPlayerSurname.text = player.lastName.uppercase()
                txtPlayerTeam.text = player.team.fullName
                txtPosition.text = player.position
                txtHeight.text = "HEIGHT: ${player.height} FEET"
                txtWeight.text = "WEIGHT: ${player.weight} POUNDS"
                progressBar.visibility = View.INVISIBLE
                viewBackground.visibility = View.INVISIBLE
            }
        })


    }
}