package com.esaudev.pokemondemo.presentation.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.esaudev.pokemondemo.R
import com.esaudev.pokemondemo.databinding.ItemPokemonStatBinding
import com.esaudev.pokemondemo.domain.model.Stat
import com.esaudev.pokemondemo.util.BaseListViewHolder

class PokemonStatListAdapter(

): ListAdapter<Stat, BaseListViewHolder<*>>(DiffUtilCallback) {

    private object DiffUtilCallback : DiffUtil.ItemCallback<Stat>() {
        override fun areItemsTheSame(oldItem: Stat, newItem: Stat): Boolean = oldItem.name == newItem.name
        override fun areContentsTheSame(oldItem: Stat, newItem: Stat): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListViewHolder<*> {
        val itemBinding = ItemPokemonStatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindViewHolderList(itemBinding,  parent.context)
    }

    override fun onBindViewHolder(holder: BaseListViewHolder<*>, position: Int) {
        when (holder) {
            is BindViewHolderList -> holder.bind(getItem(position), position)
        }
    }

    inner class BindViewHolderList(private val binding: ItemPokemonStatBinding, private val parentContext: Context) : BaseListViewHolder<Stat>(binding.root) {

        override fun bind(item: Stat, position: Int) = with(binding) {

            tvStatName.text = getStatFormattedByName(statName = item.name)
            tvStatValue.text = item.value.toString()
            lpbStat.progress = item.value

            lpbStat.setIndicatorColor(if (item.value >= 80) {
                ContextCompat.getColor(parentContext, R.color.green)
            } else {
                ContextCompat.getColor(parentContext, R.color.red)
            })
        }

        private fun getStatFormattedByName(statName: String): String {
            return when(statName) {
                "hp" -> "Hp"
                "attack" -> "Attack"
                "defense" -> "Defense"
                "special-attack" -> "Sp. Attack"
                "special-defense" -> "Sp. Defense"
                "speed" -> "Speed"
                else -> ""
            }
        }
    }

}