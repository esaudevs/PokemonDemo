package com.esaudev.pokemondemo.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.esaudev.pokemondemo.databinding.LoadStateItemPokemonBinding

class PokemonLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PokemonLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val itemBinding = LoadStateItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(itemBinding)
    }

    inner class LoadStateViewHolder(private val binding: LoadStateItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {

            with(binding) {
                loadStateRetry.isVisible = loadState !is LoadState.Loading
                loadStateErrorMessage.isVisible = loadState !is LoadState.Loading
                loadStateProgress.isVisible = loadState is LoadState.Loading

                if (loadState is LoadState.Error) {
                    loadStateErrorMessage.text = loadState.error.localizedMessage
                }

                loadStateRetry.setOnClickListener { retry.invoke() }
            }
        }
    }
}