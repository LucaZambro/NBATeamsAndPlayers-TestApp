package com.example.nbateamsandplayers_testapp.presentation.teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nbateamsandplayers_testapp.databinding.TeamsFragmentBinding
import com.example.nbateamsandplayers_testapp.presentation.adapters.TeamAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamsFragment : Fragment() {

    private val viewModel: TeamsViewModel by viewModels()
    private lateinit var binding: TeamsFragmentBinding
    private var adapter: TeamAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = TeamsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.getTeams().observe(viewLifecycleOwner, { teams ->
            adapter?.dataSet = teams

            binding.progressBar.visibility = View.INVISIBLE
            binding.viewBackground.visibility = View.INVISIBLE
        })

    }

    private fun setupRecyclerView() {
        binding.apply {
            adapter = TeamAdapter()
            recyclerTeams.layoutManager = GridLayoutManager(requireContext(), 2)
            recyclerTeams.adapter = adapter
        }
    }

}