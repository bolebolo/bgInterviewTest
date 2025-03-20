package com.betsson.interviewtest.utils

import androidx.recyclerview.widget.DiffUtil
import com.betsson.interviewtest.models.Bet

// DiffUtil callback for comparing Bet items based on type and content
class BetDiffCallback : DiffUtil.ItemCallback<Bet>() {
    override fun areItemsTheSame(oldItem: Bet, newItem: Bet): Boolean = oldItem.type == newItem.type
    override fun areContentsTheSame(oldItem: Bet, newItem: Bet): Boolean = oldItem == newItem
}