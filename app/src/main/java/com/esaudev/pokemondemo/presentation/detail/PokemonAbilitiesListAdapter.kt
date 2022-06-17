package com.esaudev.pokemondemo.presentation.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.esaudev.pokemondemo.databinding.ItemPokemonAbilityBinding
import com.esaudev.pokemondemo.domain.extensions.capSentence
import com.esaudev.pokemondemo.util.BaseListViewHolder

class PokemonAbilitiesListAdapter(

): ListAdapter<String, BaseListViewHolder<*>>(DiffUtilCallback) {

    private object DiffUtilCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListViewHolder<*> {
        val itemBinding = ItemPokemonAbilityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindViewHolderList(itemBinding,  parent.context)
    }

    override fun onBindViewHolder(holder: BaseListViewHolder<*>, position: Int) {
        when (holder) {
            is BindViewHolderList -> holder.bind(getItem(position), position)
        }
    }

    inner class BindViewHolderList(private val binding: ItemPokemonAbilityBinding, private val parentContext: Context) : BaseListViewHolder<String>(binding.root) {

        override fun bind(item: String, position: Int) = with(binding) {

            val abilityName = if (position == currentList.lastIndex) "${item.capSentence()} (hidden)" else item.capSentence()
            tvAbilityName.text = abilityName
        }
    }

}