package com.esaudev.pokemondemo.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.esaudev.pokemondemo.R
import com.esaudev.pokemondemo.databinding.FragmentPokemonListBinding
import com.esaudev.pokemondemo.domain.model.Pokemon
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonListFragment : Fragment() {

    private var _binding: FragmentPokemonListBinding? = null
    private val binding: FragmentPokemonListBinding
        get() = _binding!!

    private val viewModel: PokemonListViewModel by viewModels()

    private val pokemonListPagingAdapter by lazy {
        PokemonListPagingAdapter {
            navigateToPokemonDetail(pokemon = it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)

        // Binding Ui events
        initComponents()
        subscribeViewModel()

        return binding.root
    }

    private fun initComponents() {

        binding.rvPokemonList.apply {
            adapter = pokemonListPagingAdapter.withLoadStateFooter(
                footer = PokemonLoadStateAdapter{ pokemonListPagingAdapter.retry() }
            )
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            overScrollMode = View.OVER_SCROLL_NEVER
            setHasFixedSize(false)
        }

    }

    private fun subscribeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pokemonListPaging
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collectLatest { pokemonListPagingAdapter.submitData(it) }

        }
    }

    private fun navigateToPokemonDetail(pokemon: Pokemon) {
        val action = PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(
            pokemonId = pokemon.id
        )
        findNavController().navigate(action)
    }

}