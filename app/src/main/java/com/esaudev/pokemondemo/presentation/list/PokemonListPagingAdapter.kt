package com.esaudev.pokemondemo.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.esaudev.pokemondemo.databinding.ItemPokemonBinding
import com.esaudev.pokemondemo.domain.extensions.capSentence
import com.esaudev.pokemondemo.domain.extensions.load
import com.esaudev.pokemondemo.domain.model.Pokemon

class PokemonListPagingAdapter(
    private val pokemonClickListener: (pokemon: Pokemon) -> Unit
): PagingDataAdapter<Pokemon, PokemonListPagingAdapter.ViewHolder>(DiffUtilCallBack){


    inner class ViewHolder(private val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        init {
            binding.root.setOnClickListener {
                val cryptoItem = getItem(absoluteAdapterPosition)
                cryptoItem?.let { pokemonClickListener(it) }
            }
        }

        fun bind(item: Pokemon) {
            with(binding) {
                tvPokemonName.text = item.name.capSentence()
                ivPokemonImage.load(item.image)
            }
        }
    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

}