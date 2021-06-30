package com.example.nbateamsandplayers_testapp.presentation.players

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbateamsandplayers_testapp.databinding.PlayersFragmentBinding
import com.example.nbateamsandplayers_testapp.presentation.adapters.PlayerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayersFragment : Fragment() {

    private val viewModel: PlayersViewModel by viewModels()
    private lateinit var binding: PlayersFragmentBinding
    private var adapter: PlayerAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = PlayersFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.edtTxtSearch.doAfterTextChanged {
            viewModel.search(it?.trim().toString())
        }

        setupRecyclerView()
        //viewmodel
        viewModel.getPlayers().observe(viewLifecycleOwner, { players ->
            adapter?.dataSet = players
        })


    }

    private fun setupRecyclerView() {
        binding.apply {
            adapter = PlayerAdapter()
            recyclerPlayers.layoutManager = LinearLayoutManager(requireContext())
            recyclerPlayers.adapter = adapter

            adapter?.onPlayerClickListener = { player ->
                val action = PlayersFragmentDirections.actionPlayersFragmentToDetailFragment(player.id)
                findNavController().navigate(action)
            }
        }
    }
}