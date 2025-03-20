package com.betsson.interviewtest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.betsson.interviewtest.R
import com.betsson.interviewtest.databinding.ListItemBinding
import com.betsson.interviewtest.models.Bet
import com.betsson.interviewtest.utils.BetDiffCallback
import com.betsson.interviewtest.utils.loadImage

// Used ListAdapter for automatic diffing and optimized item updates, instead of RecyclerView.Adapter, for better performance and easier data management
class BetAdapter : ListAdapter<Bet, BetAdapter.ViewHolder>(BetDiffCallback()) {

    class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Bet) {
            binding.image.loadImage(item.image)
            binding.type.text = item.type.displayName
            binding.odds.text = binding.root.context.getString(R.string.odds_format, item.odds)
            binding.sellIn.text = binding.root.context.getString(R.string.sell_in_format, item.sellIn)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
