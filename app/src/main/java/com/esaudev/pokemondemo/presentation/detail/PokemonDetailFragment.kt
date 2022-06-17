package com.esaudev.pokemondemo.presentation.detail

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.esaudev.pokemondemo.R
import com.esaudev.pokemondemo.databinding.FragmentPokemonDetailBinding
import com.esaudev.pokemondemo.databinding.FragmentPokemonListBinding
import com.esaudev.pokemondemo.domain.extensions.*
import com.esaudev.pokemondemo.domain.model.PokemonDetail
import com.esaudev.pokemondemo.domain.model.PokemonTypes
import com.esaudev.pokemondemo.util.Resource
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {

    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding: FragmentPokemonDetailBinding
        get() = _binding!!

    private val viewModel: PokemonDetailViewModel by viewModels()
    private val pokemonStatListAdapter by lazy { PokemonStatListAdapter() }
    private val pokemonAbilitiesListAdapter by lazy { PokemonAbilitiesListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)

        // Binding Ui events
        initComponents()
        subscribeViewModel()

        return binding.root
    }

    private fun initComponents() {
        binding.rvPokemonStats.apply {
            adapter = pokemonStatListAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            overScrollMode = View.OVER_SCROLL_NEVER
            setHasFixedSize(false)
        }

        binding.rvPokemonAbilities.apply {
            adapter = pokemonAbilitiesListAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            overScrollMode = View.OVER_SCROLL_NEVER
            setHasFixedSize(false)
        }
        binding.bBack.setOnClickListener { onBackPressed() }
    }

    private fun subscribeViewModel() {
        viewModel.pokemonDetail.observe(viewLifecycleOwner) { state ->
            when(state) {
                is Resource.Success -> handlePokemonDetail(pokemonDetail = state.data)
                is Resource.Loading -> showLoadingScreen()
                is Resource.Error -> Unit
                else -> Unit
            }
        }
    }

    private fun handlePokemonDetail(pokemonDetail: PokemonDetail) {

        initPokemonTypes(pokemonDetail.types)

        with(binding) {
            tvPokemonName.text = pokemonDetail.name.capSentence()
            tvPokemonNumber.text = getPokemonNumber(pokemonId = pokemonDetail.id)
            tvPokemonHeight.text = getPokemonHeight(pokemonDetail = pokemonDetail)
            tvPokemonWeight.text = getPokemonWeight(pokemonDetail = pokemonDetail)

            ivPokemonImage.load(pokemonDetail.image)

            pokemonStatListAdapter.submitList(pokemonDetail.stats)
            pokemonAbilitiesListAdapter.submitList(pokemonDetail.abilities)
        }

        showContentScreen()
    }

    private fun showContentScreen() {
        with(binding) {
            svLoading.hide()
            svContent.showWithFade()
        }
    }

    private fun showLoadingScreen() {
        with(binding) {
            svContent.hide()
            svLoading.showWithFade()
        }
    }

    private fun getPokemonNumber(pokemonId: Int): String {
        return when(pokemonId.toString().length) {
            1 -> "#00$pokemonId"
            2 -> "#0$pokemonId"
            3 -> "#$pokemonId"
            else -> "#000"
        }
    }

    private fun getPokemonHeight(pokemonDetail: PokemonDetail): String {
        val pokemonHeight: Float = pokemonDetail.height.toFloat()/10
        return "Height: $pokemonHeight cm"
    }

    private fun getPokemonWeight(pokemonDetail: PokemonDetail): String {
        val pokemonWeight: Float = pokemonDetail.weight.toFloat()/10
        return "Weight: $pokemonWeight kg"
    }

    private fun initPokemonTypes(pokemonTypes: List<String>) {
        pokemonTypes.forEach { createPokemonTypeChip(it) }
    }

    private fun createPokemonTypeChip(pokemonType: String) {
        val chip = Chip(requireContext())
        val drawable = ChipDrawable.createFromAttributes(
            requireContext(),
            null,
            0,
            R.style.Widget_Pokemon_Chip_Choice
        )
        chip.setChipDrawable(drawable)
        chip.text = pokemonType.capSentence()
        chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        chip.chipBackgroundColor = getTypeBg(pokemonType)
        chip.isClickable = false

        chip.height = requireContext().dpToPx(56)
        chip.setPadding(
            requireContext().dpToPx(16),
            0,
            requireContext().dpToPx(16),
            0
        )

        binding.cgTypes.addView(chip)
    }

    private fun getTypeBg(pokemonType: String): ColorStateList? {
        return when(PokemonTypes.valueOf(pokemonType)) {
            PokemonTypes.normal -> ContextCompat.getColorStateList(requireContext(), R.color.normal)
            PokemonTypes.fire -> ContextCompat.getColorStateList(requireContext(), R.color.fire)
            PokemonTypes.water -> ContextCompat.getColorStateList(requireContext(), R.color.water)
            PokemonTypes.electric -> ContextCompat.getColorStateList(requireContext(), R.color.electric)
            PokemonTypes.grass -> ContextCompat.getColorStateList(requireContext(), R.color.grass)
            PokemonTypes.ice -> ContextCompat.getColorStateList(requireContext(), R.color.ice)
            PokemonTypes.fighting -> ContextCompat.getColorStateList(requireContext(), R.color.fighting)
            PokemonTypes.poison -> ContextCompat.getColorStateList(requireContext(), R.color.poison)
            PokemonTypes.ground -> ContextCompat.getColorStateList(requireContext(), R.color.ground)
            PokemonTypes.flying -> ContextCompat.getColorStateList(requireContext(), R.color.flying)
            PokemonTypes.psychic -> ContextCompat.getColorStateList(requireContext(), R.color.psychic)
            PokemonTypes.bug -> ContextCompat.getColorStateList(requireContext(), R.color.bug)
            PokemonTypes.rock -> ContextCompat.getColorStateList(requireContext(), R.color.rock)
            PokemonTypes.ghost -> ContextCompat.getColorStateList(requireContext(), R.color.ghost)
            PokemonTypes.dragon -> ContextCompat.getColorStateList(requireContext(), R.color.dragon)
            PokemonTypes.dark -> ContextCompat.getColorStateList(requireContext(), R.color.dark)
            PokemonTypes.steel -> ContextCompat.getColorStateList(requireContext(), R.color.steel)
            PokemonTypes.fairy -> ContextCompat.getColorStateList(requireContext(), R.color.fairy)
            else -> ContextCompat.getColorStateList(requireContext(), R.color.normal)
        }
    }

}